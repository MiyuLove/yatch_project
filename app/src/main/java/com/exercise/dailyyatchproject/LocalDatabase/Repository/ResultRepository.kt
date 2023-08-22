package com.exercise.dailyyatchproject.LocalDatabase.Repository

import com.exercise.dailyyatchproject.LocalDatabase.Entity.ResultEntity
import com.exercise.dailyyatchproject.LocalDatabase.YatchDatabase
import com.exercise.dailyyatchproject.UtilSupply.GlobalApplication

class ResultRepository {
    private val context = GlobalApplication.context()

    private val db = YatchDatabase.getDatabase(context)

    fun create(resultEntity: ResultEntity) = db.resultDao().create(resultEntity)

    fun read() = db.resultDao().read()

    fun update(resultEntity: ResultEntity) = db.resultDao().update(resultEntity)

    fun delete(resultEntity: ResultEntity) = db.resultDao().delete(resultEntity)

    fun deleteAllData() = db.resultDao().deleteAllData()
}
