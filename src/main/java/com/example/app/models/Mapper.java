package com.example.app.models;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface Mapper {

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
     * Get a model with id.
     * @param id id of the target model
     * @param tableName The table to be operated on
     * @return required model
     */
    Model getById(long id, String tableName);

    /**
     * Insert a model.
     * @param model The model to be inserted
     * @param tableName The table to be operated on
     * @return id
     */
    long insert(Model model, String tableName);

}
