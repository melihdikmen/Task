package com.example.task.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "process")
class DTOProcess {
    @PrimaryKey(autoGenerate = false)
    @NonNull
    @ColumnInfo(name = "id")
    @SerializedName("id")
     lateinit var id: String

    @ColumnInfo(name = "name")
    @SerializedName("name")
      var name: String? = null

    @ColumnInfo(name = "surname")
    @SerializedName("surname")
     var surname: String? = null

    @ColumnInfo(name = "photo")
    @SerializedName("photo")
     lateinit var photo: String

    @ColumnInfo(name = "status")
    @SerializedName("status")
     lateinit var status: String


}