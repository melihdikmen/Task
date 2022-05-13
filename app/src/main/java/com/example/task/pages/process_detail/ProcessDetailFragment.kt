package com.example.task.pages.process_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.task.R
import com.example.task.adapters.ProcessAdapter
import com.example.task.databinding.FragmentProcessDetailBinding
import com.example.task.databinding.FragmentProcessListBinding
import com.example.task.model.DTOProcess
import com.example.task.model.DTOProcessDetail
import com.example.task.pages.process_list.ProcessListViewModel
import com.example.task.utils.StringUtils

class ProcessDetailFragment : Fragment() {
    private lateinit var binding: FragmentProcessDetailBinding
    private lateinit var processDetailViewModel: ProcessDetailViewModel
    private lateinit var process:DTOProcess
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var processId: String? = arguments?.getString("process")
        process = DTOProcess()
        if (processId != null) {
            process.id = processId
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentProcessDetailBinding.inflate(inflater, container, false)
        processDetailViewModel = ViewModelProvider(this).get(ProcessDetailViewModel::class.java)

        val observer = Observer<DTOProcessDetail>() { processDetail ->

            binding.txtId.text = processDetail.processId
            if( processDetail.name != null){
                binding.txtName.text = processDetail.name +" "+ processDetail.surname
            }

            binding.txtIdentityNumber.text = processDetail.number

            binding.txtFatherName.text = processDetail.fatherName
            binding.txtMotherName.text = processDetail.motherName
            binding.txtGender.text = processDetail.gender
            binding.txtBirthday.text = processDetail.birthday
            binding.txtBirthPlace.text = processDetail.birthPlace
            binding.txtMaritalStatus.text  = processDetail.maritalStatus
            binding.txtCity.text = processDetail.city
            binding.txtTown.text = processDetail.town

            if(processDetail.dateOfDeath != null){
                binding.txtDateOfDeath.text = processDetail.dateOfDeath
            }

         binding.imageView.setImageBitmap(   StringUtils.base64ToBitmap(processDetail.photo!!))

        };
        processDetailViewModel.processDetail.observe(this,observer)

        return binding.root
    }



    override fun onResume() {
        super.onResume()
        processDetailViewModel.getProcessDetail(process,context!!)
    }


}