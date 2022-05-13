package com.example.task.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.task.model.DTOProcess

@Dao
interface ProcessDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(process: DTOProcess)


    @Query("SELECT*FROM process")
    fun getAllProcess(): List<DTOProcess>



}