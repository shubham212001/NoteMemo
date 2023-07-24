package com.sharma.notememo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.task_item.view.*
import java.text.SimpleDateFormat
import java.util.*


class task_adapter(val list: List<FirestoreTask>, val Listener: listener) : RecyclerView.Adapter<task_adapter.TodoViewHolder>() {

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
        fun bind(firestoreTask: FirestoreTask) {
            with(itemView) {
                user_title.text = firestoreTask.title?.toUpperCase()
                user_descr.text = firestoreTask.description?.toUpperCase()
                user_date.text = firestoreTask.date
                user_time.text = firestoreTask.time
            }
        }

        private fun updateTime(time: Long) {
            val myformat = "h:mm a"
            val sdf = SimpleDateFormat(myformat)
            itemView.user_time.text = sdf.format(Date(time))
        }

        private fun updateDate(time: Long) {
            val myformat = "EEE, d MMM yyyy"
            val sdf = SimpleDateFormat(myformat)
            itemView.user_date.text = sdf.format(Date(time))
        }
    }
}

interface listener {
    fun delete_task(input: FirestoreTask)
    fun share(input: FirestoreTask)
    fun update_task(input: FirestoreTask)
}

