package com.example.app.models;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MapperTest {

    protected static SqlSessionFactory createSqlSessionFactory() throws IOException {
        Reader reader = Resources.getResourceAsReader("database.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
        return factory;
    }

}
