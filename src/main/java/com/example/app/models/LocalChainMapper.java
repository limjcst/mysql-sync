package com.example.app.models;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface LocalChainMapper extends Mapper<LocalChain> {

    /**
     * Create schema.
     * @param tableName The table to be operated on
     */
    @Update({
        "CREATE TABLE IF NOT EXISTS ${tablename} (",
        "   `No` BIGINT UNSIGNED AUTO_INCREMENT,",
        "   `msgTime` BIGINT UNSIGNED,",
        "   `startTime` BIGINT UNSIGNED,",
        "   `elapsedTime` DOUBLE UNSIGNED,",
        "   `success` VARCHAR(7),",
        "   `traceId` VARCHAR(63),",
        "   `id` VARCHAR(63),",
        "   `pid` VARCHAR(63),",
        "   `cmdb_id` VARCHAR(20),",
        "   `serviceName` VARCHAR(20),",
        "   `dsName` VARCHAR(20),",
        "   INDEX (startTime),",
        "   PRIMARY KEY (`No`))",
    })
    void schema(@Param("tablename") String tableName);

    /**
     * Get indices with range of id.
     * @param start start id of the target index range
     * @param end next id of the target index range
     * @param tableName The table to be operated on
     * @return required index
     */
    @Select({
        "SELECT * FROM ${tablename} WHERE No >= #{start} and No < #{end} ORDER BY No ASC"
    })
    @Results({
        @Result(column = "No", property = "no", jdbcType = JdbcType.BIGINT, id = true),
        @Result(column = "msgTime", property = "msgTime", jdbcType = JdbcType.BIGINT),
        @Result(column = "startTime", property = "startTime", jdbcType = JdbcType.BIGINT),
        @Result(column = "elapsedTime", property = "elapsedTime", jdbcType = JdbcType.DOUBLE),
        @Result(column = "success", property = "success", jdbcType = JdbcType.VARCHAR),
        @Result(column = "traceId", property = "traceId", jdbcType = JdbcType.VARCHAR),
        @Result(column = "id", property = "id", jdbcType = JdbcType.VARCHAR),
        @Result(column = "pid", property = "pid", jdbcType = JdbcType.VARCHAR),
        @Result(column = "cmdb_id", property = "cmdbId", jdbcType = JdbcType.VARCHAR),
        @Result(column = "serviceName", property = "serviceName", jdbcType = JdbcType.VARCHAR),
        @Result(column = "dsName", property = "dsName", jdbcType = JdbcType.VARCHAR),
    })
    List<LocalChain> getByRange(@Param("start") long start, @Param("end") long end,
                             @Param("tablename") String tableName);

    /**
     * Insert a DSChain.
     * @param chain The DSChain to be inserted
     * @param tableName The table to be operated on
     * @return id
     */
    @Insert({
        "INSERT INTO ${tablename} (No, msgTime, startTime, elapsedTime, success, traceId,",
        "   id, pid, cmdb_id, serviceName, dsName)",
        "VALUES (#{model.no}, #{model.msgTime}, #{model.startTime}, #{model.elapsedTime},",
        "   #{model.success}, #{model.traceId}, #{model.id}, #{model.pid}, #{model.cmdbId},",
        "   #{model.serviceName}, #{model.dsName})",
    })
    long insert(@Param("model") LocalChain chain, @Param("tablename") String tableName);

}
