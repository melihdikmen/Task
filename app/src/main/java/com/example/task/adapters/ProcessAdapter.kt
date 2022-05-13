package com.example.task.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.task.R
import com.example.task.interfaces.ProcessItemListener
import com.example.task.model.DTOProcess

class ProcessAdapter(
    private var processList: List<DTOProcess>,
    private var processItemListener: ProcessItemListener
) :
    RecyclerView.Adapter<ProcessAdapter.ProcessViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProcessViewHolder {
        var row: View =
            LayoutInflater.from(parent.context).inflate(R.layout.list_process_item, parent, false)
        return ProcessViewHolder(row)
    }

    override fun onBindViewHolder(holder: ProcessViewHolder, position: Int) {
        holder.setIsRecyclable(false)
        var process: DTOProcess? = processList?.get(position) ?: null

        if (process != null) {
            holder.bin(process, position, processItemListener)
        }

    }

    override fun getItemCount(): Int {

        return processList.size

    }


    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }


    override fun setHasStableIds(hasStableIds: Boolean) {
        super.setHasStableIds(hasStableIds)
    }


    class ProcessViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var orderText: TextView = itemView.findViewById<TextView>(R.id.txt_order)
        private var txtProcessId: TextView = itemView.findViewById(R.id.txtProcessId)
        private var txtStatus: TextView = itemView.findViewById(R.id.txtStatus)
        private var txtName: TextView = itemView.findViewById(R.id.txtName)
        private var btnDetail: Button = itemView.findViewById(R.id.btnDetail)

        fun bin(process: DTOProcess, position: Int, listener: ProcessItemListener) {

            orderText.text = (position + 1).toString()
            if (process.name == null)
                txtName.text = " - "
            else
                txtName.text = process.name + " " + process.surname

            txtProcessId.text = process.id
            txtStatus.text = process.status


            btnDetail.setOnClickListener(View.OnClickListener { view ->

                listener.openDetail(process)

            })

        }
    }
}