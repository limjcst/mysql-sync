package com.example.app.models;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface ServiceChainMapper extends Mapper {

    /**
     * Create schema.
     * @param tableName The table to be operated on
     */
    @Update({
        "CREATE TABLE IF NOT EXISTS ${tablename} (",
        "   `No` INT UNSIGNED AUTO_INCREMENT,",
        "   `startTime` BIGINT UNSIGNED,",
        "   `elapsedTime` DOUBLE UNSIGNED,",
        "   `success` VARCHAR(7),",
        "   `traceId` VARCHAR(63),",
        "   `id` VARCHAR(63),",
        "   `serviceName` VARCHAR(15),",
        "   PRIMARY KEY (`No`))",
    })
    void schema(@Param("tablename") String tableName);

    /**
     * Get an index with id.
     * @param id id of the target index
     * @param tableName The table to be operated on
     * @return required index
     */
    @Select({
        "SELECT * FROM ${tablename} WHERE No = #{id}"
    })
    @Results({
        @Result(column = "No", property = "no", jdbcType = JdbcType.BIGINT, id = true),
        @Result(column = "startTime", property = "startTime", jdbcType = JdbcType.BIGINT),
        @Result(column = "elapsedTime", property = "elapsedTime", jdbcType = JdbcType.DOUBLE),
        @Result(column = "success", property = "success", jdbcType = JdbcType.VARCHAR),
        @Result(column = "traceId", property = "traceId", jdbcType = JdbcType.VARCHAR),
        @Result(column = "id", property = "id", jdbcType = JdbcType.VARCHAR),
        @Result(column = "serviceName", property = "serviceName", jdbcType = JdbcType.VARCHAR),
    })
    ServiceChain getById(@Param("id") long id, @Param("tablename") String tableName);

    /**
     * Insert a ServiceChain.
     * @param chain The ServiceChain to be inserted
     * @param tableName The table to be operated on
     * @return id
     */
    @Insert({
        "INSERT INTO ${tablename} (No, startTime, elapsedTime, success, traceId, id, serviceName)",
        "VALUES (#{model.no}, #{model.startTime}, #{model.elapsedTime}, #{model.success},",
        "   #{model.traceId}, #{model.id}, #{model.serviceName})",
    })
    long insert(@Param("model") ServiceChain chain, @Param("tablename") String tableName);

}
