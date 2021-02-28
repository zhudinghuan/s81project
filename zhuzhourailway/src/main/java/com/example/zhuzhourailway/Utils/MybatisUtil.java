//package com.example.zhuzhourailway.Utils;
//
//import org.apache.ibatis.io.Resources;
//import org.apache.ibatis.session.SqlSession;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.apache.ibatis.session.SqlSessionFactoryBuilder;
//
//import java.io.IOException;
//import java.io.InputStream;
//
//public class MybatisUtil {
//    //   SqlSessionFactory 要保持单例模式，所以写在静态代码块里面，只能运行一次
//    //   sqlSession是线程不安全的，所以每次请求都创建一次
//    private static SqlSessionFactory factory=null;
//
//    static {
//        String config="mybatis.xml";
//        InputStream in= null;
//        try {
//            in = Resources.getResourceAsStream(config);
//            SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
//            factory=builder.build(in);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//    public static SqlSession getSqlSession(){
//        SqlSession sqlSession=null;
//        if(factory!=null){
//            sqlSession= factory.openSession();
//        }
//        return sqlSession;
//
//    }
//}
