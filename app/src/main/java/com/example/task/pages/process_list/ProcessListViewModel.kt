package com.example.task.pages.process_list

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.task.db.DatabaseManager
import com.example.task.model.DTOProcess
import com.example.task.model.DTOResponse
import com.example.task.net.ApiRequestListener
import com.example.task.net.ApiRequests
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class ProcessListViewModel : ViewModel() {

    private lateinit var databaseManager: DatabaseManager

    private var allProcess: MutableLiveData<List<DTOProcess>> = MutableLiveData()

    var isLoading: MutableLiveData<Boolean> = MutableLiveData()

    var allProcessResult: List<DTOProcess>? = null

    fun getProcessListFromServer(context: Context) {
        isLoading.value = true

        var listener = object : ApiRequestListener<DTOResponse<ArrayList<DTOProcess>>> {
            override fun onResponse(response: DTOResponse<ArrayList<DTOProcess>>) {
                isLoading.value = false
                if (response.status == 200) {
                    databaseManager = DatabaseManager.getDatabaseManager(context)!!

                    for (item in response.data!!) {
                        databaseManager.processDao().insert(item)
                    }

                    allProcessResult =
                        databaseManager.processDao().getAllProcess()

                    allProcess.value = allProcessResult

                } else {
                    show("The data could not get. An error has occurred on server.", context)
                }

                Log.d("result", response.status.toString())

            }

            override fun onFailure(t: Throwable?) {
                show("The data could not get. The request could not complete.", context)
                Log.d("result", t.toString())
                isLoading.value = true
            }
        }

        GlobalScope.async { ApiRequests.getProcess(listener); }

    }


    fun getProcessList(): MutableLiveData<List<DTOProcess>> {
        return this.allProcess
    }


    fun show(message: String, context: Context) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun search(text: String) {
        if (allProcessResult != null && allProcessResult!!.isNotEmpty()) {
            var result = allProcessResult?.filter {
                it.name?.lowercase()?.contains(text.lowercase()) ?: false || it.id.lowercase()
                    .contains(text.lowercase()) || it.surname?.lowercase()
                    ?.contains(text.lowercase()) ?: false || it.status.lowercase()
                    .contains(text.lowercase())
            }
            allProcess.value = result
        } else {
            allProcess.value = allProcessResult
        }

    }

}