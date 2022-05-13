package com.example.task.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.task.model.DTOProcess
import com.example.task.model.DTOProcessDetail

@Dao
interface ProcessDetailDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(process: DTOProcessDetail)


    @Query("SELECT*FROM process_detail where id  = :id")
    fun getProcessDetailId(id:String): List<DTOProcessDetail>
}