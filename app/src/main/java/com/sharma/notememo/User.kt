package com.sharma.notememo

import androidx.room.Entity
import androidx.room.PrimaryKey
//The below defined is the name of the table
@Entity
data class entity (
    val title:String,
    val description:String,
    val date:String,
    val time:String,

){
    @PrimaryKey(autoGenerate = true)
    var id:Int=0
}
