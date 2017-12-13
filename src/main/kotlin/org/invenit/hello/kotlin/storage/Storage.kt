package org.invenit.hello.kotlin.storage

import org.invenit.hello.kotlin.model.Entity

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
object Storage {
    var storage: MutableMap<String, MutableMap<Int, Entity>> = mutableMapOf()
    var sequences: MutableMap<String, Int> = mutableMapOf()

    private fun getTable(name: String): MutableMap<Int, Entity> {
        if (storage[name] == null) {
            storage[name] = mutableMapOf()
        }
        return storage[name]!!
    }

    private fun getNextId(table: String): Int {
        if (sequences[table] == null) {
            sequences[table] = 1
        }
        val nextId = sequences[table]!!
        sequences[table] = nextId + 1
        return nextId
    }


    fun <T : Entity> findAll(tableName: String, tClass: Class<T>): List<T> {
        return storage[tableName]?.map { tClass.cast(it.value) } ?: emptyList()
    }

    fun <T : Entity> findOne(tableName: String, id: Int, tClass: Class<T>): T? {
        val entity = storage[tableName]?.get(id)
        return tClass.cast(entity)
    }

    fun <T : Entity> findWhere(tableName: String, tClass: Class<T>, expression: (entity : T) -> Boolean): List<T> {
        val table = getTable(tableName)
        return table.map { tClass.cast(it.value) }.filter(expression)
    }

    fun <T : Entity> add(tableName: String, value: T): T {
        val id = getNextId(tableName)
        value.id = id
        getTable(tableName)[id] = value
        return value
    }

    fun <T : Entity> update(tableName: String, id: Int, value: T): T {
        val table = getTable(tableName)
        if (table[id] == null) {
            throw IllegalArgumentException("Entity #$id in table $table not found")
        }
        table[id] = value
        value.id = id
        return value
    }

    fun delete(tableName: String, id: Int) {
        storage[tableName]?.remove(id)
    }

    fun <T: Entity> deleteWhere(tableName: String, tClass: Class<T>, expression: (entity: T) -> Boolean) {
        val table = getTable(tableName)
        table.map { tClass.cast(it.value)}
                .filter(expression)
                .forEach { table.remove(it.id)}
    }

    fun exists(tableName: String, id: Int): Boolean {
        return storage[tableName]?.get(id) != null
    }
}