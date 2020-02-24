package com.example.app.models;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface EventMapper extends Mapper {

    /**
     * Create schema.
     */
    @Update({
        "CREATE TABLE IF NOT EXISTS event (",
        "   `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,",
        "   `time` BIGINT NOT NULL,",
        "   PRIMARY KEY (`id`))",
    })
    void schema();

    /**
     * Get the id of the latest event.
     * @return id of the latest event, 0 if there is no event
     */
    @Select({
        "SELECT COALESCE(MAX(id), 0) FROM event"
    })
    Long getLatestId();

    /**
     * Get an event with id.
     * @param id id of the target event
     * @return required event
     */
    @Select({
        "SELECT * FROM event WHERE id = #{id}"
    })
    @Results({
        @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
        @Result(column = "time", property = "time", jdbcType = JdbcType.BIGINT),
    })
    Event getById(long id);

    /**
     * Insert an event.
     * @param event The event to be inserted
     * @return id
     */
    @Insert({
        "INSERT INTO event (id, time)",
        "VALUES (#{id}, #{time})"
    })
    long insert(Event event);

}
