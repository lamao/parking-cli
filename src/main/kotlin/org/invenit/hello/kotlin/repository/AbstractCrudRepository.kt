package org.invenit.hello.kotlin.repository

import org.invenit.hello.kotlin.model.Entity

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
abstract class AbstractCrudRepository<T : Entity>: CrudRepository<Int, T> {
    private var nextId: Int = 100
    private val entities: MutableMap<Int, T> = mutableMapOf()

    override fun save(entity: T): T {
        if (entity.id == null) {
            entity.id = nextId
            nextId++
        }

        // TODO Refactor
        val entityId = entity.id ?: throw IllegalArgumentException("ID is null")
        entities[entityId] = entity
        return entity
    }

    override fun getAll(): List<T> {
        return entities.values.toList()
    }

    override fun get(id: Int): T? {
        return entities[id]
    }
}