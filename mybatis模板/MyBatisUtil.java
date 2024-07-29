package com.jesnen.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisUtil {
    private MyBatisUtil() {}

    private static String resource = "mybatis-config.xml";

    private static SqlSessionFactory sqlSessionFactory=null;

    static {//配置文件路径

        try {
            //基于配置文件路径，创建字节输入流对象
            InputStream is = Resources.getResourceAsStream(resource);
            //创建SqlSessionFactory工厂类对象
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static SqlSession openSession(){
        //使用SqlSessionFactory工厂类。创建SqlSession对象
        return sqlSessionFactory.openSession();
    }

    public static SqlSession openSession(boolean flag){
        //使用SqlSessionFactory工厂类。创建SqlSession对象
        return sqlSessionFactory.openSession(flag);
    }

    public static void closeSqlSession(SqlSession sqlSession){
        if (sqlSession!=null){
            sqlSession.commit();
            sqlSession.close();
        }
    }


}
