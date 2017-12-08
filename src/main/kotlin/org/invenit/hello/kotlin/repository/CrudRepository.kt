package org.invenit.hello.kotlin.repository

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
interface CrudRepository<in ID, T> {
    fun save(entity: T): T
    fun getAll(): List<T>
    fun getWhere(expression: (T) -> Boolean): List<T>
    fun get(id: ID): T?
    fun exists(id: ID): Boolean
}