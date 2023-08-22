package com.exercise.dailyyatchproject.LocalDatabase.Repository

import com.exercise.dailyyatchproject.LocalDatabase.Entity.ResultEntity
import com.exercise.dailyyatchproject.LocalDatabase.Entity.UserEntity
import com.exercise.dailyyatchproject.LocalDatabase.YatchDatabase
import com.exercise.dailyyatchproject.UtilSupply.GlobalApplication

class UserRepository {
    private val context = GlobalApplication.context()

    private val db = YatchDatabase.getDatabase(context)

    fun create(userEntity: UserEntity) = db.userDao().create(userEntity)

    fun read() = db.userDao().read()

    fun update(userEntity: UserEntity) = db.userDao().update(userEntity)

    fun delete(userEntity: UserEntity) = db.userDao().delete(userEntity)

    fun deleteAllData() = db.userDao().deleteAllData()
}