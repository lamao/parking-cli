package org.invenit.hello.kotlin.repository

import org.invenit.hello.kotlin.model.Entity
import org.invenit.hello.kotlin.storage.Storage

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
abstract class AbstractCrudRepository<T : Entity>(val tableName: String, val tClass: Class<T>) : CrudRepository<Int, T> {
    private val storage = Storage
//    val storage = StorageJava()

    override fun save(entity: T): T {
        if (entity.id == null) {
            return storage.add(tableName, entity)
        } else {
            return storage.update(tableName, entity.id!!, entity)
        }
    }

    override fun getAll(): List<T> {
        return storage.findAll(tableName, tClass)
    }

    override fun getWhere(expression: (T) -> Boolean): List<T> {
        return storage.findWhere(tableName, tClass, expression)
    }

    override fun get(id: Int): T? {
        return storage.findOne(tableName, id, tClass)
    }

    override fun exists(id: Int): Boolean {
        return storage.exists(tableName, id)
    }
}