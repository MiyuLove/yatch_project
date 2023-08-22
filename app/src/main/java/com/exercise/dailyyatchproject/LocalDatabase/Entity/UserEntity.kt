package com.exercise.dailyyatchproject.LocalDatabase.Entity

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "nickname")
    val text: String,

    @ColumnInfo(name = "photo")
    val profilePhoto: Bitmap?
)