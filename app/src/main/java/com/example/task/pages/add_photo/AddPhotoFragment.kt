package com.example.task.pages.add_photo

import android.Manifest.permission.*
import android.app.Activity
import android.app.Dialog
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.task.R
import com.example.task.databinding.FragmentAddPhoto2Binding
import com.example.task.utils.FileConvertUtils
import com.victor.loading.rotate.RotateLoading


class AddPhotoFragment : Fragment() {
    private lateinit var binding: FragmentAddPhoto2Binding
    private lateinit var  appPhotoViewModel: AddPhotoViewModel
    private  lateinit var processDialog : Dialog
    private var rotateLoading: RotateLoading? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        processDialog = Dialog(context!!,R.style.CustomDialog)
        processDialog.setContentView(R.layout.dialog_process)
        processDialog.setCanceledOnTouchOutside(false)

        rotateLoading = processDialog.findViewById<View>(R.id.rotateloading) as RotateLoading

        binding = FragmentAddPhoto2Binding.inflate(inflater,container,false)
        appPhotoViewModel = ViewModelProvider(this).get(AddPhotoViewModel::class.java)

        binding.btnFromCamera.setOnClickListener(View.OnClickListener { view->

            checkPermissionForCamera()
        })

        binding.btnFromGallery.setOnClickListener(View.OnClickListener { view->

            checkPermissionForGallery()

        })


        binding.btnSubmit.setOnClickListener(View.OnClickListener { view->
            appPhotoViewModel.uploadPhoto(activity!!)

        })

        appPhotoViewModel.isLoading.observe(this,Observer<Boolean>{ isLoading ->

            if(isLoading){
                processDialog.show()
                rotateLoading!!.start()
            }
            else{
                processDialog.dismiss()
                rotateLoading!!.stop()
            }
        } )



        return binding.root
    }


    private val requestPermissionReadAndWrite =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
//            permissions.entries.forEach {
//
//            }
            if (permissions[READ_EXTERNAL_STORAGE] == true && permissions[WRITE_EXTERNAL_STORAGE] == true) {
                appPhotoViewModel.openGallery(activity!!,startForResultForGallery)
            } else {
                   appPhotoViewModel.show("Read Permission Denied",activity!!);
            }
        }



    private val requestPermissionCamera =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->

            if (permissions[CAMERA] == true ) {
                appPhotoViewModel.takeFromCamera(activity!!,startForResultForCamera)
            } else {
                appPhotoViewModel.show("Camera Permission Denied",activity!!);
            }
        }


    val startForResultForGallery = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            val uri = result.data!!.data

            val bitmap = MediaStore.Images.Media.getBitmap(context!!.contentResolver,uri)

            appPhotoViewModel.file = FileConvertUtils.bitmapToFile(bitmap,"TempPhoto",context!!)

            binding.imageView.setImageURI(uri)

        }
    }

    val startForResultForCamera = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {

            val data = result.data!!.extras!!.get("data") as Bitmap
            appPhotoViewModel.file = FileConvertUtils.bitmapToFile(data,"TempPhoto",context!!)
            binding.imageView.setImageBitmap(data)
        }
    }




    private fun checkPermissionForGallery() {
        if (context?.let {
                ContextCompat.checkSelfPermission(
                    it,
                    READ_EXTERNAL_STORAGE
                )
            } != PackageManager.PERMISSION_GRANTED) {

            requestPermissionReadAndWrite.launch(
                arrayOf(READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE))
        } else {
            appPhotoViewModel.openGallery(activity!!,startForResultForGallery)
        }
    }

    private fun checkPermissionForCamera(){
        if (context?.let {
                ContextCompat.checkSelfPermission(
                    it,
                    CAMERA
                )
            } != PackageManager.PERMISSION_GRANTED) {

           requestPermissionCamera.launch(arrayOf(CAMERA))
        } else {
            appPhotoViewModel.takeFromCamera(activity!!,startForResultForCamera)
        }
    }





}