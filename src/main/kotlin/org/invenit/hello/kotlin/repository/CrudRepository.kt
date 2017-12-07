package org.invenit.hello.kotlin.repository

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
interface CrudRepository<ID, T> {
    fun save(entity: T): T
    fun getAll(): List<T>
    fun get(id: ID): T?
}