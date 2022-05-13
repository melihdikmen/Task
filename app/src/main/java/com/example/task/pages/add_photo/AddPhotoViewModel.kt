package com.example.task.pages.add_photo

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.app.ActivityOptionsCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.task.db.DatabaseManager
import com.example.task.model.DTOProcessDetail
import com.example.task.model.DTOResponse
import com.example.task.model.DTOUploadResult
import com.example.task.net.ApiRequestListener
import com.example.task.net.ApiRequests
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.io.File
import java.util.jar.Manifest

class AddPhotoViewModel : ViewModel() {

    var file: File? = null
    var isLoading : MutableLiveData<Boolean> = MutableLiveData()


    fun openGallery(activity: FragmentActivity, startForResult: ActivityResultLauncher<Intent>) {
        val intent = Intent("android.intent.action.GET_CONTENT")
        intent.type = "image/*"
        startForResult.launch(intent)
    }

    fun takeFromCamera(activity: FragmentActivity, startForResult: ActivityResultLauncher<Intent>) {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startForResult.launch(cameraIntent)
    }


    fun show(message: String, activity: FragmentActivity) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }


    fun uploadPhoto(activity: FragmentActivity) {

        isLoading.value = true

        var listener = object : ApiRequestListener<DTOUploadResult> {
            override fun onResponse(response: DTOUploadResult) {
                isLoading.value = false
                if (response.status == 200) {
                    show("The photo uploaded successfully.",activity)
                } else {
                    show("The photo could not be loaded.",activity)
                }



                Log.d("result", response.status.toString())


            }

            override fun onFailure(t: Throwable?) {
                // TODO istek yapılamadı hata mesajı
                show("The photo could not be loaded.",activity)
                Log.d("result", t.toString())
                isLoading.value = false
            }
        }


        if (file != null) {
            GlobalScope.async { ApiRequests.uploadPhoto(listener, file!!); }

        } else {
            isLoading.value = false
            show("The any photo has  not selected.",activity)
        }

    }


}