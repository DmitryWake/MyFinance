package com.ewake.myfinance.data.database.base

import androidx.room.*

/**
 * @author Nikolaevsky Dmitry (@d.nikolaevskiy)
 */

interface BaseDao<Entity> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: Entity)
    @Update
    fun update(entity: Entity)
    @Delete
    fun delete(entity: Entity)
}