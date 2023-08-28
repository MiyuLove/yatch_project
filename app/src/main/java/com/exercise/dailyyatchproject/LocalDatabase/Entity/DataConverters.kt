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
    fun fromListIntToString(intList : List<Int>) : String = intList.toString()

    @TypeConverter
    fun toListIntFromString(stringList : String) : List<Int>{
        val result = ArrayList<Int>()
        val split =stringList.replace("[","").replace("]","").replace(" ","").split(",")
        for (n in split) {
            try {
                result.add(n.toInt())
            } catch (e: Exception) {

            }
        }
        return result
    }

    @TypeConverter
    fun fromListStringToString(stringList : List<String>) : String = stringList.toString()

    @TypeConverter
    fun toListStringFromString(stringList : String) : List<String>{
        val result = ArrayList<String>()
        val split =stringList.replace("[","").replace("]","").replace(" ","").split(",")
        for (n in split) {
            try {
                result.add(n)
            } catch (e: Exception) {

            }
        }
        return result
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