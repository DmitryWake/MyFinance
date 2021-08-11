package com.ewake.myfinance.data.database.base

import androidx.room.*

/**
 * @author Nikolaevsky Dmitry (@d.nikolaevskiy)
 */

interface BaseDao<Entity> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: Entity)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(list: List<Entity>)
    @Update
    fun update(entity: Entity)
    @Delete
    fun delete(entity: Entity)
}