package com.exercise.dailyyatchproject.LocalDatabase.Entity

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.TypeConverter
import java.io.ByteArrayOutputStream
import java.util.Date

class DataConverters{
    @TypeConverter
    fun fromTimestampToDate(value : Long) : Date {
        return Date(value)
    }

    @TypeConverter
    fun fromDateToTimeStamp(date : Date) : Long{
        return date.time
    }

    @TypeConverter
    fun fromBitmapToByteArray(bitmap: Bitmap) : ByteArray {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        return outputStream.toByteArray()
    }

    @TypeConverter
    fun fromByteArrayToBitmap(byteArray: ByteArray) : Bitmap {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }
}