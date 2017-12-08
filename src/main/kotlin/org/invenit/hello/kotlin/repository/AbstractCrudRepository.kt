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
        return storage.getAll(tableName, tClass)
    }

    override fun get(id: Int): T? {
        return storage.getOne(tableName, id, tClass)
    }
}