package com.example.task.pages.process_detail

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.task.db.DatabaseManager
import com.example.task.model.DTOProcess
import com.example.task.model.DTOProcessDetail
import com.example.task.model.DTOResponse
import com.example.task.net.ApiRequestListener
import com.example.task.net.ApiRequests
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async


class ProcessDetailViewModel : ViewModel() {

    private lateinit var databaseManager: DatabaseManager

    private lateinit var processDetails: List<DTOProcessDetail>

    var processDetail: MutableLiveData<DTOProcessDetail> = MutableLiveData()


    fun getProcessDetail(process: DTOProcess, context: Context) {

        databaseManager = DatabaseManager.getDatabaseManager(context)!!

        var processDetails: List<DTOProcessDetail> =
            databaseManager.processDetailDao().getProcessDetailId(process.id)

        var listener = object : ApiRequestListener<DTOResponse<DTOProcessDetail>> {
            override fun onResponse(response: DTOResponse<DTOProcessDetail>) {

                if (response.status == 200) {
                    databaseManager = DatabaseManager.getDatabaseManager(context)!!


                    databaseManager.processDetailDao().insert(response.data!!)

                    processDetails =
                        databaseManager.processDetailDao().getProcessDetailId(process.id)
                    if (processDetails.isNotEmpty())
                        processDetail.value = processDetails.get(0)
                } else {

                    // TODO istek başarı değil hata mesajı göster
                }
                Log.d("result", response.status.toString())
            }

            override fun onFailure(t: Throwable?) {
                // TODO istek yapılamadı hata mesajı
                Log.d("result", t.toString())
            }
        }

        if (processDetails.isEmpty()) {
            GlobalScope.async { ApiRequests.getProcessDetail(listener, process); }
        } else {
            processDetail.value = processDetails.get(0)

        }

    }


}
