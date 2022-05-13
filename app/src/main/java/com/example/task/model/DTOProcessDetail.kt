package com.example.task.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import org.jetbrains.annotations.NotNull

@Entity(tableName = "process_detail")
 class DTOProcessDetail {
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    @NotNull
    @SerializedName("id")
      var processId: String? = null;

    @ColumnInfo(name = "name")
    @SerializedName("name")
      var name: String? = null;

    @ColumnInfo(name = "surname")
    @SerializedName("surname")
      var surname: String? = null;

    @ColumnInfo(name = "photo")
    @SerializedName("photo")
      var photo: String? = null;

    @ColumnInfo(name = "identity_number")
    @SerializedName("identity_number")
      var number :  String? =null;

    @ColumnInfo(name = "fatherName")
    @SerializedName("father_name")
      var fatherName :  String? =null;


    @ColumnInfo(name = "motherName")
    @SerializedName("mother_name")
      var motherName :  String? =null;

    @ColumnInfo(name = "gender")
    @SerializedName("gender")
      var gender :  String? =null;

    @ColumnInfo(name = "birthday")
    @SerializedName("birthday")
      var birthday :  String? =null;


    @ColumnInfo(name = "birthPlace")
    @SerializedName("birth_place")
      var birthPlace :  String? =null;

    @ColumnInfo(name = "maritalStatus")
    @SerializedName("marital_status")
      var maritalStatus :  String? =null;

    @ColumnInfo(name = "city")
    @SerializedName("city")
      var city :  String? =null;

    @ColumnInfo(name = "town")
    @SerializedName("town")
      var town :  String? =null;

    @ColumnInfo(name = "dateOfDeath")
    @SerializedName("date_of_death")
      var dateOfDeath :  String? =null;


}