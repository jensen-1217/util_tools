package com.itheima.sh.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/*
    工具类特点：
        1.位于util包
        2.私有化构造方法
        3.提供静态方法
 */
public class SqlSessionUtil {
    private static SqlSessionFactory factory = null;

    // 2.私有化构造方法
    private SqlSessionUtil() {
    }

    //静态代码块：类加载就执行获取工厂对象
    static {
        try {
            //1.获取工厂创造类对象
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            //2.获取会话工厂对象
            factory = builder.build(Resources.getResourceAsStream("mybatis-config.xml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 3.提供静态方法
    //提供会话对象，设置自动提交事务
    public static SqlSession getSqlSession() {

        SqlSession sqlSession = factory.openSession(true);
        //返回会话对象
        return sqlSession;
    }

    //提供会话对象，设置自动提交事务
    public static SqlSession getSqlSession(boolean isAutoCommit) {
        SqlSession sqlSession = factory.openSession(isAutoCommit);
        //返回会话对象
        return sqlSession;
    }

    //编写静态方法接收会话对象，手动提交事务并且关闭会话对象
    public static void commitAndClose(SqlSession sqlSession) {
        //防止空指针，判断
        if (sqlSession != null) {
            //一切正常提交事务
            sqlSession.commit();
            //关闭会话对象
            sqlSession.close();
        }
    }

    public static void commit(SqlSession sqlSession) {
        //防止空指针，判断
        if (sqlSession != null) {
            //一切正常提交事务
            sqlSession.commit();

        }
    }

    public static void close(SqlSession sqlSession) {
        //防止空指针，判断
        if (sqlSession != null) {
           sqlSession.close();

        }
    }

    //编写静态方法接收会话对象，回滚事务并且关闭会话对象
    public static void rollbackAndClose(SqlSession sqlSession) {
        //防止空指针，判断
        if (sqlSession != null) {
            //出现正常回顾事务
            sqlSession.rollback();
            //关闭会话对象
            sqlSession.close();
        }
    }

}
