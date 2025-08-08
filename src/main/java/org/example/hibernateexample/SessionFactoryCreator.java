package org.example.hibernateexample;

import org.example.hibernateexample.entity.Detail;
import org.example.hibernateexample.entity.Employee;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

public class SessionFactoryCreator {
    static SessionFactory factory;
    public static SessionFactory getSessionFactory(){
        if(factory == null){
            Properties properties = new Properties();
            properties.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
            properties.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/my_db?useSSL=false&serverTimezone=UTC");
            properties.setProperty("hibernate.connection.username", "your username");
            properties.setProperty("hibernate.connection.password", "your password");
            properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
            properties.setProperty("hibernate.show_sql", "true");

            factory = new Configuration()
                    .setProperties(properties)
                    .addAnnotatedClass(Employee.class)
                    .addAnnotatedClass(Detail.class)
                    .buildSessionFactory();
        }
        return factory;
    }
}
