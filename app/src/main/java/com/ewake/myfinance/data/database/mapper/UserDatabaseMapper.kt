package com.ewake.myfinance.data.database.mapper

import com.ewake.myfinance.data.database.entity.UserSettingsEntity
import com.ewake.myfinance.ui.model.UserSettingsModel
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

/**
 * @author Nikolaevsky Dmitry (@d.nikolaevskiy)
 */
@Mapper
interface UserDatabaseMapper : BaseDatabaseMapper<UserSettingsEntity, UserSettingsModel> {
    companion object : UserDatabaseMapper by INSTANCE
}

private val INSTANCE = Mappers.getMapper(UserDatabaseMapper::class.java)