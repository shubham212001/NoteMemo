package com.sharma.notememo

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface task_dao {

    @Insert
    suspend fun add_task(input: entity): Long


    @Query("Select * from entity order by id DESC")
    fun get_all(): LiveData<List<entity>>

    @Query("Select * from entity order by id ASC")
    fun get_all1(): LiveData<List<entity>>

    //Query for deletion of an item from the list
    @Delete
    suspend fun delete_task(input: entity)
}