package com.example.app.models;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface PlatformIndexMapper extends Mapper<PlatformIndex> {

    /**
     * Create schema.
     * @param tableName The table to be operated on
     */
    @Update({
        "CREATE TABLE IF NOT EXISTS ${tablename} (",
        "   `No` BIGINT UNSIGNED AUTO_INCREMENT,",
        "   `itemid` BIGINT UNSIGNED,",
        "   `name` VARCHAR(63),",
        "   `timestamp` BIGINT UNSIGNED,",
        "   `value` VARCHAR(1024),",
        "   `cmdb_id` VARCHAR(20),",
        "   INDEX (timestamp),",
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
        @Result(column = "No", property = "id", jdbcType = JdbcType.BIGINT, id = true),
        @Result(column = "itemid", property = "itemId", jdbcType = JdbcType.BIGINT),
        @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
        @Result(column = "timestamp", property = "timestamp", jdbcType = JdbcType.BIGINT),
        @Result(column = "value", property = "value", jdbcType = JdbcType.VARCHAR),
        @Result(column = "cmdb_id", property = "cmdbId", jdbcType = JdbcType.VARCHAR),
    })
    List<PlatformIndex> getByRange(@Param("start") long start, @Param("end") long end,
                                   @Param("tablename") String tableName);

    /**
     * Insert a PlatformIndex.
     * @param index The PlatformIndex to be inserted
     * @param tableName The table to be operated on
     * @return id
     */
    @Insert({
        "INSERT INTO ${tablename} (No, itemid, name, timestamp, value, cmdb_id)",
        "VALUES (#{model.id}, #{model.itemId}, #{model.name}, #{model.timestamp},",
        "   #{model.value}, #{model.cmdbId})",
    })
    long insert(@Param("model") PlatformIndex index, @Param("tablename") String tableName);

}
