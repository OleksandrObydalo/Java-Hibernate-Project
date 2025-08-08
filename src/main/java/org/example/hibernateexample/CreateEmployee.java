package org.example.hibernateexample;

import org.example.hibernateexample.entity.Employee;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Properties;


public class Test1 {

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        properties.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/my_db?useSSL=false&serverTimezone=UTC");
        properties.setProperty("hibernate.connection.username", "your username");
        properties.setProperty("hibernate.connection.password", "your password");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.setProperty("hibernate.show_sql", "true");

        SessionFactory factory = new Configuration()
                .setProperties(properties)
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

        try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                Employee employee = new Employee("Johny", "English", "Intelligence", 5000);
                session.save(employee);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        } finally {
            factory.close();
        }
    }
}
