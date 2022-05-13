package com.example.task.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.task.model.DTOProcess
import com.example.task.model.DTOProcessDetail

@Database(entities = [DTOProcess::class,DTOProcessDetail::class],version = 1)
abstract class DatabaseManager : RoomDatabase() {

    abstract fun processDao(): ProcessDao
    abstract fun processDetailDao(): ProcessDetailDao

    companion object {

        private var instance: DatabaseManager? = null

        fun getDatabaseManager(context: Context): DatabaseManager? {

            if (instance == null) {
                instance = Room.databaseBuilder(context, DatabaseManager::class.java, "process.db")
                    .allowMainThreadQueries().build()
            }
            return instance
        }

    }
}