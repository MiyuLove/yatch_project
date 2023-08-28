package com.exercise.dailyyatchproject.LocalDatabase.Entity

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import java.io.ByteArrayOutputStream
import java.util.Date

@Entity(tableName = "result_table")
data class ResultEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id : Int,

    @ColumnInfo(name = "users")
    val userList : List<String>,
    @ColumnInfo(name = "scores")
    val scoreList : List<Int>,
    @ColumnInfo(name = "DateTime")
    val dateTime : Date
)