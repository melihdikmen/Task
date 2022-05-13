package com.example.task.pages

import android.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.task.adapters.ProcessAdapter
import com.example.task.databinding.ActivityMainBinding
import com.example.task.interfaces.ProcessItemListener
import com.example.task.model.DTOProcess


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



//        processListViewModel = ViewModelProvider(this).get(ProcessListViewModel::class.java)
//
//
//        //        swipeRefreshLayout = root.findViewById(R.id.swipe_refresh_layout);
//        binding.recyclerView.setHasFixedSize(true)
//        //recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
//        //recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
//        layoutManager = LinearLayoutManager(this)
//        binding.recyclerView.layoutManager = layoutManager
//
//
//
//
//        var processItemListener = object : ProcessItemListener {
//            override fun openDetail(process: DTOProcess) {
//
//            }
//        }
//
//
//        val observer = Observer<List<DTOProcess>>() { processList ->
//            binding.recyclerView.adapter = ProcessAdapter(processList,processItemListener )
//
//        };
//
//        processListViewModel.getProcessList().observe(this, observer)

    }


    override fun onResume() {
        super.onResume()


    }


}

