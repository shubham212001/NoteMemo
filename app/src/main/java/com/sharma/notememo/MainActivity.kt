package com.sharma.notememo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


open class MainActivity : AppCompatActivity(),listener {


    val db by lazy {
        task_database.getDatabase(this)
    }
    val list = arrayListOf<entity>()
    var adapter = task_adapter(list,this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        todorv.apply {
//            layoutManager = LinearLayoutManager(this@MainActivity)
//            adapter = this.adapter
//        }
        todorv.layoutManager= LinearLayoutManager(this)
        var Adapter =todorv.adapter
        todorv.adapter=adapter


        db.todoDao().get_all().observe(this, Observer {

            list.clear()
            list.addAll(it)
            adapter.notifyDataSetChanged()


        })
        addbutton.setOnClickListener{
            val intent= Intent(this,task_activity::class.java)
            startActivity(intent)
        }

        addbutton.setOnClickListener {
            val intent = Intent(this, task_activity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            R.id.history -> {
//                startActivity(Intent(this, HistoryActivity::class.java))
//            }
//        }
        when (item.itemId) {
            R.id.sort -> {
                GlobalScope.launch (Dispatchers.Main){
                    db.todoDao().get_all1().observe(this@MainActivity, Observer {

                        list.clear()
                        list.addAll(it)
                        adapter.notifyDataSetChanged()


                    }) }

            }
            R.id.sort1 -> {
                GlobalScope.launch (Dispatchers.Main){
                    db.todoDao().get_all().observe(this@MainActivity, Observer {

                    list.clear()
                    list.addAll(it)
                    adapter.notifyDataSetChanged()


                }) }


            }
        }
        return super.onOptionsItemSelected(item)
    }
    override fun delete_task(input: entity) {
        GlobalScope.launch (Dispatchers.Main){
            db.todoDao().delete_task(input)
            db.todoDao().get_all().observe(this@MainActivity, Observer {

                list.clear()
                list.addAll(it)
                adapter.notifyDataSetChanged()


            })
        }

    }

    override fun share(input: entity) {
      GlobalScope.launch (Dispatchers.Main){
         var title=input.title
          var description=input.description
          var final_shareable_data=title+"\n"+description
          val shareIntent = Intent()
          shareIntent.action = Intent.ACTION_SEND
          shareIntent.type="text/plain"
          shareIntent.putExtra(Intent.EXTRA_TEXT, final_shareable_data);
          startActivity(Intent.createChooser(shareIntent,"Share Title and Description"))
      }
    }

    override fun update_task(input: entity) {
        GlobalScope.launch (Dispatchers.Main){
            var title=input.title
            var description=input.description
            var date=input.date
            var time=input.time
            val intent=Intent(this@MainActivity,update_activiity::class.java)
            intent.putExtra("edit_title",title)
            intent.putExtra("edit_description",description)
            intent.putExtra("edit_date",date)
            intent.putExtra("edit_time",time)
            startActivity(intent)
        }
    }

    fun updater(){
        db.todoDao().get_all().observe(this@MainActivity, Observer {

            list.clear()
            list.addAll(it)
            adapter.notifyDataSetChanged()


        })
    }
}

