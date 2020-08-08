package com.example.surviveinsubria.objects;

import java.util.HashMap;
import java.util.Map;

public abstract class ObjectDatabase {
    protected String primaryKey;

    protected ObjectDatabase() {

    }

    protected ObjectDatabase(String pk) {
        primaryKey = pk;
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public Map<String, Object> toDatabase() {
        Map<String, Object> mappa = new HashMap<>();
        mappa.put("id", primaryKey);

        return mappa;
    }

    abstract public ObjectDatabase fromDatabase(Map<String, Object> object);
}
