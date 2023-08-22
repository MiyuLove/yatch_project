package com.exercise.dailyyatchproject.LocalDatabase.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.exercise.dailyyatchproject.LocalDatabase.Entity.ResultEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ResultDao {
    @Query("SELECT * FROM result_table")
    fun read() : Flow<List<ResultEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun create(user : ResultEntity)

    @Update
    fun update(userEntity: ResultEntity)

    @Delete
    fun delete(userEntity: ResultEntity)

    @Query("DELETE FROM result_table")
    fun deleteAllData()
}