package com.sharma.notememo

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.window.SplashScreen
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.sharma.notememo.auth.SplashActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch



class MainActivity : AppCompatActivity(), listener {
    val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    val tasksRef: CollectionReference = firestore.collection("tasks")

    private lateinit var db: FirebaseFirestore
    private val list = arrayListOf<FirestoreTask>()
    private var adapter = task_adapter(list, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize Firebase Firestore
        db = FirebaseFirestore.getInstance()

        todorv.layoutManager = LinearLayoutManager(this)
        todorv.adapter = adapter

        db.collection("tasks").addSnapshotListener { value, error ->
            if (error != null) {
                Log.w(TAG, "Listen failed.", error)
                return@addSnapshotListener
            }

            val tasks = ArrayList<FirestoreTask>()
            for (doc in value!!) {
                doc.toObject(FirestoreTask::class.java)?.let {
                    it.id = doc.id
                    tasks.add(it)
                }
            }
            list.clear()
            list.addAll(tasks)
            adapter.notifyDataSetChanged()
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
        when (item.itemId) {
            R.id.sort -> {
                // Load and sort data from Firestore in ascending order
                tasksRef.orderBy("title", Query.Direction.ASCENDING)
                    .get()
                    .addOnSuccessListener { result ->
                        val sortedTasks = result.toObjects(FirestoreTask::class.java)
                        list.clear()
                        list.addAll(sortedTasks)
                        adapter.notifyDataSetChanged()
                    }
            }
            R.id.sort1 -> {
                // Load and sort data from Firestore in descending order
                tasksRef.orderBy("title", Query.Direction.DESCENDING)
                    .get()
                    .addOnSuccessListener { result ->
                        val sortedTasks = result.toObjects(FirestoreTask::class.java)
                        list.clear()
                        list.addAll(sortedTasks)
                        adapter.notifyDataSetChanged()
                    }
            }
            R.id.signout -> {
                // Sign out the user
                FirebaseAuth.getInstance().signOut()
                // Return to Splash Screen
                startActivity(Intent(this, SplashActivity::class.java))
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }



    override fun delete_task(input: FirestoreTask) {
        db.collection("tasks").document(input.id)
            .delete()
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully deleted!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error deleting document", e) }
    }

    override fun share(input: FirestoreTask) {
        val title = input.title
        val description = input.description
        val final_shareable_data = "$title\n$description"
        val shareIntent = Intent()
        shareIntent.action = Intent.ACTION_SEND
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT, final_shareable_data)
        startActivity(Intent.createChooser(shareIntent, "Share Title and Description"))
    }

    override fun update_task(input: FirestoreTask) {
        TODO("Not yet implemented")
    }
}



