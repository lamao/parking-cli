package org.invenit.hello.kotlin.repository

import org.invenit.hello.kotlin.model.Entity
import org.invenit.hello.kotlin.storage.Storage

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
abstract class AbstractCrudRepository<T : Entity>(
        private val tableName: String,
        private val tClass: Class<T>
) : CrudRepository<Int, T> {

    private val storage = Storage

    override fun save(entity: T): T {
        if (entity.id == 0) {
            return storage.add(tableName, entity)
        } else {
            return storage.update(tableName, entity.id, entity)
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

    override fun delete(id: Int) {
        storage.delete(tableName, id)
    }

    override fun deleteWhere(expression: (T) -> Boolean) {
        storage.deleteWhere(tableName, tClass, expression)
    }
}