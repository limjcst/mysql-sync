package com.example.app.models;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface BusinessIndexMapper extends Mapper {

    /**
     * Create schema.
     * @param tableName The table to be operated on
     */
    @Update({
        "CREATE TABLE IF NOT EXISTS ${tablename} (",
        "   `No` INT UNSIGNED AUTO_INCREMENT,",
        "   `serviceName` VARCHAR(15),",
        "   `startTime` BIGINT UNSIGNED,",
        "   `avg_time` DOUBLE UNSIGNED,",
        "   `num` INT UNSIGNED,",
        "   `succee_num` INT UNSIGNED,",
        "   `succee_rate` DOUBLE UNSIGNED,",
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
        @Result(column = "No", property = "id", jdbcType = JdbcType.BIGINT, id = true),
        @Result(column = "serviceName", property = "serviceName", jdbcType = JdbcType.VARCHAR),
        @Result(column = "startTime", property = "startTime", jdbcType = JdbcType.BIGINT),
        @Result(column = "avg_time", property = "avgTime", jdbcType = JdbcType.DOUBLE),
        @Result(column = "num", property = "num", jdbcType = JdbcType.BIGINT),
        @Result(column = "succee_num", property = "succeeNum", jdbcType = JdbcType.BIGINT),
        @Result(column = "succee_rate", property = "succeeRate", jdbcType = JdbcType.DOUBLE),
    })
    BusinessIndex getById(@Param("id") long id, @Param("tablename") String tableName);

    /**
     * Insert a BusinessIndex.
     * @param index The BusinessIndex to be inserted
     * @param tableName The table to be operated on
     * @return id
     */
    @Insert({
        "INSERT INTO ${tablename} (No, serviceName, startTime, avg_time, num, succee_num, succee_rate)",
        "VALUES (#{model.id}, #{model.serviceName}, #{model.startTime}, #{model.avgTime}, #{model.num},",
        "   #{model.succeeNum}, #{model.succeeRate})",
    })
    long insert(@Param("model") BusinessIndex index, @Param("tablename") String tableName);

}