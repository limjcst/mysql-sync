package com.example.app.models;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface BusinessIndexMapper extends Mapper<BusinessIndex> {

    /**
     * Create schema.
     * @param tableName The table to be operated on
     */
    @Update({
        "CREATE TABLE IF NOT EXISTS ${tablename} (",
        "   `No` BIGINT UNSIGNED AUTO_INCREMENT,",
        "   `serviceName` VARCHAR(20),",
        "   `startTime` BIGINT UNSIGNED,",
        "   `avg_time` DOUBLE UNSIGNED,",
        "   `num` INT UNSIGNED,",
        "   `succee_num` INT UNSIGNED,",
        "   `succee_rate` DOUBLE UNSIGNED,",
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
        @Result(column = "No", property = "id", jdbcType = JdbcType.BIGINT, id = true),
        @Result(column = "serviceName", property = "serviceName", jdbcType = JdbcType.VARCHAR),
        @Result(column = "startTime", property = "startTime", jdbcType = JdbcType.BIGINT),
        @Result(column = "avg_time", property = "avgTime", jdbcType = JdbcType.DOUBLE),
        @Result(column = "num", property = "num", jdbcType = JdbcType.BIGINT),
        @Result(column = "succee_num", property = "succeeNum", jdbcType = JdbcType.BIGINT),
        @Result(column = "succee_rate", property = "succeeRate", jdbcType = JdbcType.DOUBLE),
    })
    List<BusinessIndex> getByRange(@Param("start") long start, @Param("end") long end,
                                   @Param("tablename") String tableName);

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
