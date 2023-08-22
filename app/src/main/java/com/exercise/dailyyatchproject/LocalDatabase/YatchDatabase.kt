package com.exercise.dailyyatchproject.LocalDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.exercise.dailyyatchproject.LocalDatabase.Dao.ResultDao
import com.exercise.dailyyatchproject.LocalDatabase.Dao.UserDao
import com.exercise.dailyyatchproject.LocalDatabase.Entity.DataConverters
import com.exercise.dailyyatchproject.LocalDatabase.Entity.ResultEntity
import com.exercise.dailyyatchproject.LocalDatabase.Entity.UserEntity

@Database(entities = [ResultEntity::class, UserEntity::class], version = 3)
@TypeConverters(DataConverters::class)
abstract class YatchDatabase : RoomDatabase(){

    abstract fun resultDao() : ResultDao
    abstract fun userDao() : UserDao

    companion object{

        @Volatile
        private var INSTANCE : YatchDatabase? = null

        fun getDatabase(
            context: Context
        ) : YatchDatabase{

            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    YatchDatabase::class.java,
                    "result_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}