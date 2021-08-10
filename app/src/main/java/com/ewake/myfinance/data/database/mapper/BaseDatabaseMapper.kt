package com.ewake.myfinance.data.database.mapper

/**
 * @author Nikolaevsky Dmitry (@d.nikolaevskiy)
 */
interface BaseDatabaseMapper<Entity, Model> {
    fun entityToModel(entity: Entity): Model
    fun entityListToModelList(list: List<Entity>): List<Model>
    fun modelToEntity(model: Model): Entity
    fun modelListToEntityList(list: List<Model>): List<Entity>
}