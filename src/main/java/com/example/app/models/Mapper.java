package com.example.app.models;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface Mapper<M extends Model> {

    /**
     * Count models.
     * @param tableName The table to be operated on
     * @return number of models
     */
    @Select({
        "SELECT COUNT(*) FROM ${tablename}"
    })
    long getCount(@Param("tablename") String tableName);

    /**
     * Get the id of the latest model.
     * @param tableName The table to be operated on
     * @return id of the latest model, 0 if there is no model
     */
    @Select({
        "SELECT COALESCE(MAX(No), 0) FROM ${tablename}"
    })
    Long getLatestId(@Param("tablename") String tableName);

    /**
     * Get indices with range of id.
     * @param start start id of the target index range
     * @param end next id of the target index range
     * @param tableName The table to be operated on
     * @return required model
     */
    List<M> getByRange(long start, long end, String tableName);

    /**
     * Insert a model.
     * @param model The model to be inserted
     * @param tableName The table to be operated on
     * @return id
     */
    long insert(M model, String tableName);

}
