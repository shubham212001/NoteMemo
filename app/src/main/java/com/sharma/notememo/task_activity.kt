package com.sharma.notememo

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_task_activity.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

const val DB_NAME = "todo.db"
class task_activity : AppCompatActivity() {
    val db = FirebaseFirestore.getInstance()
    val taskCollection = db.collection("tasks")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_activity)

        //supportActionBar?.hide()
        val list = arrayListOf<entity>()
       // var adapter = task_adapter(list)

        val db by lazy {
            task_database.getDatabase(this)
        }

        this.getSupportActionBar()?.hide()


//        update_button.setOnClickListener {
//            val title = edit_title.text.toString()
//            val description = edit_desc.text.toString()
//            val date = edit_date.text.toString()
//            val time = edit_time.text.toString()
//            if (title.length != 0 && description.length != 0 && date.length != 0 && time.length != 0) {
//
//                GlobalScope.launch(Dispatchers.Main) {
//                    val id = withContext(Dispatchers.IO) {
//                        return@withContext db.todoDao().add_task(
//                                entity(
//                                        title,
//                                        description,
//                                        date,
//                                        time
//                                )
//                        )
//                    }
//                    finish()
//                }
//            }
//            else {
//                Toast.makeText(this,"Fields Can't be empty", Toast.LENGTH_LONG).show()
//            }
//        }
        update_button.setOnClickListener {
            val title = edit_title.text.toString()
            val description = edit_desc.text.toString()
            val date = edit_date.text.toString()
            val time = edit_time.text.toString()

            if (title.isNotEmpty() && description.isNotEmpty() && date.isNotEmpty() && time.isNotEmpty()) {
                val task = hashMapOf(
                    "title" to title,
                    "description" to description,
                    "date" to date,
                    "time" to time
                )

                    taskCollection.add(task)
                    .addOnSuccessListener { documentReference ->
                        Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                        finish()
                    }
                    .addOnFailureListener { e ->
                        Log.w(TAG, "Error adding document", e)
                    }
            } else {
                Toast.makeText(this, "Fields Can't be empty", Toast.LENGTH_LONG).show()
            }
        }







        val datePicker =
                MaterialDatePicker.Builder.datePicker()
                        .setTitleText("Select Desired date")
                        .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                        .build()
//Initialising time variable
        val time_picker =
                MaterialTimePicker.Builder()
                        .setTimeFormat(TimeFormat.CLOCK_12H)
                        .setHour(12)
                        .setTitleText("Select Desired Time")
                        .setMinute(10)
                        .build()

        GlobalScope.launch {
            edit_date.setOnClickListener {
                datePicker.show(supportFragmentManager, "tag")
            }
        }
        GlobalScope.launch {
            edit_time.setOnClickListener {
                time_picker.show(supportFragmentManager, "tag");
            }
        }



        time_picker.addOnPositiveButtonClickListener {

            val pickedHour: Int = time_picker.hour
            val pickedMinute: Int = time_picker.minute
            val formattedTime: String = when {
                pickedHour > 12 -> {
                    if (pickedMinute < 10) {
                        "${time_picker.hour - 12}:0${time_picker.minute} pm"
                    } else {
                        "${time_picker.hour - 12}:${time_picker.minute} pm"
                    }
                }
                pickedHour == 12 -> {
                    if (pickedMinute < 10) {
                        "${time_picker.hour}:0${time_picker.minute} pm"
                    } else {
                        "${time_picker.hour}:${time_picker.minute} pm"
                    }
                }
                pickedHour == 0 -> {
                    if (pickedMinute < 10) {
                        "${time_picker.hour + 12}:0${time_picker.minute} am"
                    } else {
                        "${time_picker.hour + 12}:${time_picker.minute} am"
                    }
                }
                else -> {
                    if (pickedMinute < 10) {
                        "${time_picker.hour}:0${time_picker.minute} am"
                    } else {
                        "${time_picker.hour}:${time_picker.minute} am"
                    }
                }
            }
            edit_time.setText(formattedTime)
        }
        datePicker.addOnPositiveButtonClickListener {
            val dater = datePicker.headerText.toString()
            edit_date.setText(dater)
        }
//From here we can write the desired code so that the desired thing happens


    }
}