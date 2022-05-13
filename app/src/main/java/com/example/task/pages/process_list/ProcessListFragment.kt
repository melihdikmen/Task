package com.example.task.pages.process_list

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.task.R
import com.example.task.adapters.ProcessAdapter
import com.example.task.databinding.FragmentProcessListBinding
import com.example.task.interfaces.ProcessItemListener
import com.example.task.model.DTOProcess
import com.victor.loading.rotate.RotateLoading


class ProcessListFragment : Fragment() {
    private lateinit var binding: FragmentProcessListBinding
    private lateinit var processListViewModel: ProcessListViewModel
    private var layoutManager: RecyclerView.LayoutManager? = null
    private lateinit var processDialog : Dialog;
    private  lateinit var  rotateLoading : RotateLoading
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


        binding = FragmentProcessListBinding.inflate(inflater, container, false)

        processListViewModel = ViewModelProvider(this).get(ProcessListViewModel::class.java)

        layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.layoutManager = layoutManager

        binding.fab.setOnClickListener(View.OnClickListener { view ->
            findNavController().navigate(R.id.action_processListFragment_to_addPhotoFragment)

        })

        var searchBarListener  = object : OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
               return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                Log.d("Searc",p0!!)
                    processListViewModel.search(p0)
                return true;
            }
        }

        binding.searchView.setOnQueryTextListener(searchBarListener)

        return binding.root
    }

    fun newInstance(): ProcessListFragment {
        return ProcessListFragment()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var processItemListener = object : ProcessItemListener {
            override fun openDetail(process: DTOProcess) {
                val bundle = Bundle()
                bundle.putString("process",process.id)
                findNavController().navigate(R.id.action_processListFragment_to_processDetailFragment,bundle)

            }
        }
        val observer = Observer<List<DTOProcess>>() { processList ->
            binding.recyclerView.adapter = ProcessAdapter(processList, processItemListener)

        };

        processListViewModel.getProcessList().observe(this, observer)


        processListViewModel.isLoading.observe(this, Observer { isLoading ->
                if(isLoading){
                    processDialog.show()
                    rotateLoading.start()
                }
                else {
                    processDialog.dismiss()
                    rotateLoading.stop()
                }

        })

    }


    override fun onResume() {
        super.onResume()
        processListViewModel.getProcessListFromServer(context!!)

    }


}