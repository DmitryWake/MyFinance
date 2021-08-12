package com.ewake.myfinance.data.repository

import com.ewake.myfinance.data.database.AppDatabase
import com.ewake.myfinance.data.database.mapper.UserDatabaseMapper
import com.ewake.myfinance.ui.model.UserSettingsModel
import io.reactivex.rxjava3.core.Maybe
import javax.inject.Inject

/**
 * @author Nikolaevsky Dmitry (@d.nikolaevskiy)
 */
class UserRepository @Inject constructor(appDatabase: AppDatabase): Repository() {

    private val dao = appDatabase.userDao()
    private val mapper = UserDatabaseMapper

    fun loadUser(): Maybe<UserSettingsModel> {
        return dao.getUser().map { mapper.entityToModel(it) }
    }

    fun createUser(model: UserSettingsModel) {
        dao.deleteAll()
        dao.insert(mapper.modelToEntity(model))
    }

    fun updateUser(model: UserSettingsModel) {
        dao.update(mapper.modelToEntity(model))
    }
}