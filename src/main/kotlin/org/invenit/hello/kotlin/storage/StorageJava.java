package org.invenit.hello.kotlin.storage;

import org.invenit.hello.kotlin.model.Entity;
import org.invenit.hello.kotlin.repository.CrudRepository;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Source for conversion into Kotlin Storage
 *
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
@Deprecated
public class StorageJava {
    private Map<String, Map<Integer, Entity>> storage = new HashMap<>();
    private Map<String, Integer> sequences = new HashMap<>();

    private Map<Integer, Entity> getTable(String name) {
        if (storage.get(name) == null) {
            storage.put(name, new HashMap<>());
            sequences.put(name, 1);
        }
        return storage.get(name);
    }

    private Integer getNextId(String table) {
        Integer nextId = sequences.get(table);
        sequences.put(table, nextId + 1);
        return nextId;
    }


    public <T extends Entity> List<T> getAll(String tableName, Class<T> tClass) {
        List<T> result = new ArrayList<>();
        Map<Integer, Entity> table = storage.get(tableName);
        if (table != null) {
            for (Entity e : table.values()) {
                T targetEntity = tClass.cast(e);
                result.add(targetEntity);
            }
        }
        return result;
    }

    public <T extends Entity> T getOne(String tableName, Integer id, Class<T> tClass) {
        if (storage.get(tableName) != null) {
            Entity result = storage.get(tableName).get(id);
            if (result == null) {
                return null;
            } else {
                return tClass.cast(result);
            }
        }
        return null;
    }

    public <T extends Entity> T add(String tableName, T value) {
        Integer id = getNextId(tableName);
        value.setId(id);
        getTable(tableName).put(id, value);
        return value;
    }

    public <T extends Entity> T update(String tableName, Integer id, T value) {
        Map<Integer, Entity> table = getTable(tableName);
        if (table.get(id) == null) {
            throw new IllegalArgumentException("Entity #$id in table $table not found");
        }
        table.put(id, value);
        value.setId(id);
        return value;
    }

    public void delete(String tableName, Integer id) {
        if (storage.get(tableName) != null) {
            storage.get(tableName).remove(id);
        }
    }

    private class Repository<T extends Entity> implements CrudRepository<Integer, T> {
        private StorageJava storage = new StorageJava();
        private Class<T> tClass;

        public Repository(Class<T> tClass) {
            this.tClass = tClass;
        }

        @Override
        public T save(T entity) {
            if (entity.getId() == null) {
                return storage.add("1", entity);
            } else {
                return storage.update("1", entity.getId(), entity);
            }
        }

        @NotNull
        @Override
        public List<T> getAll() {
            return storage.getAll("1", tClass);
        }

        @Nullable
        @Override
        public T get(Integer id) {
            return storage.getOne("1", id, tClass);
        }
    }
}
