package com.sharma.notememo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import kotlinx.android.synthetic.main.activity_task_activity.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

const val DB_NAME = "todo.db"
class task_activity : AppCompatActivity() {
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


        save.setOnClickListener {
            val title = title_input.text.toString()
            val description = desc_input.text.toString()
            val date = date_input.text.toString()
            val time = time_input.text.toString()
            if (title.length != 0 && description.length != 0 && date.length != 0 && time.length != 0) {

                GlobalScope.launch(Dispatchers.Main) {
                    val id = withContext(Dispatchers.IO) {
                        return@withContext db.todoDao().add_task(
                                entity(
                                        title,
                                        description,
                                        date,
                                        time
                                )
                        )
                    }
                    finish()
                }
            }
            else {
                Toast.makeText(this,"Fields Can't be empty", Toast.LENGTH_LONG).show()
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
            date_input.setOnClickListener {
                datePicker.show(supportFragmentManager, "tag")
            }
        }
        GlobalScope.launch {
            time_input.setOnClickListener {
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
            time_input.setText(formattedTime)
        }
        datePicker.addOnPositiveButtonClickListener {
            val dater = datePicker.headerText.toString()
            date_input.setText(dater)
        }
//From here we can write the desired code so that the desired thing happens


    }
}