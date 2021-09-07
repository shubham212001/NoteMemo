package com.sharma.notememo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_update_activiity.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class update_activiity : AppCompatActivity() {
    val list = arrayListOf<entity>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_activiity)
        this.getSupportActionBar()?.hide()
        var a =intent.getStringExtra("edit_title").toString()
        var b=intent.getStringExtra("edit_description").toString()
        var c=intent.getStringExtra("edit_date").toString()
        var d=intent.getStringExtra("edit_time").toString()
        editTextTextPersonName3.setText(a)
        editTextTextPersonName2.setText(b)
//        val list = arrayListOf<entity>()
//        var adapter = task_adapter(list,MainActivity)
  //      var adapter = task_adapter(list,this@update_activiity)
//        todorv.layoutManager= LinearLayoutManager(this)
//        var Adapter =todorv.adapter
//        todorv.adapter=adapter

        var title_changed=editTextTextPersonName3.text.toString()
        var desc_changed=editTextTextPersonName2.text.toString()
        val db by lazy {
            task_database.getDatabase(this)
        }
        val reference by lazy {

        }
//        todorv.layoutManager= LinearLayoutManager(this)
//        var Adapter =todorv.adapter
//        todorv.adapter=adapter
        update_button.setOnClickListener {
            GlobalScope.launch (Dispatchers.Main){

            db.todoDao().update_task(entity(title_changed,desc_changed,"",""))

            }
            val intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

    }


}