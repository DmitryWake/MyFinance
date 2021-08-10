package com.ewake.myfinance.data.database.mapper

import com.ewake.myfinance.data.database.entity.UserEntity
import com.ewake.myfinance.ui.model.UserModel
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

/**
 * @author Nikolaevsky Dmitry (@d.nikolaevskiy)
 */
@Mapper
interface UserDatabaseMapper : BaseDatabaseMapper<UserEntity, UserModel> {
    companion object : UserDatabaseMapper by INSTANCE
}

private val INSTANCE = Mappers.getMapper(UserDatabaseMapper::class.java)