package com.sharma.notememo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.task_item.view.*
import java.text.SimpleDateFormat
import java.util.*

class task_adapter(val list: List<entity>,val Listener:listener) : RecyclerView.Adapter<task_adapter.TodoViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.task_item, parent, false)
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(list[position])
        holder.itemView.delete.setOnClickListener {
                  Listener.delete_task(list[position])
        }
        holder.itemView.share.setOnClickListener {
            Listener.share(list[position])
        }
    }



    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(todoModel: entity) {
            with(itemView) {
                user_title.text = todoModel.title.toUpperCase()
                user_descr.text = todoModel.description
                user_date.text = todoModel.date
                user_time.text = todoModel.time

            }
        }
        private fun updateTime(time: Long) {
            //Mon, 5 Jan 2020
            val myformat = "h:mm a"
            val sdf = SimpleDateFormat(myformat)
            itemView.user_time.text = sdf.format(Date(time))

        }

        private fun updateDate(time: Long) {
            //Mon, 5 Jan 2020
            val myformat = "EEE, d MMM yyyy"
            val sdf = SimpleDateFormat(myformat)
            itemView.user_date.text = sdf.format(Date(time))

        }
    }

}

interface listener {
    fun delete_task(input:entity)
    fun share(input:entity)
}
