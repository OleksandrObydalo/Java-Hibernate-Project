package org.example.hibernateexample;

import org.example.hibernateexample.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

public class SelectEmployee {
    public static void main(String[] args) {
        SessionFactory factory = SessionFactoryCreator.getSessionFactory();

        try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            int id = 1;
            try {
                Employee employee = new Employee("Nicola", "Tesla", "Inventoring", 100000);
                session.save(employee);
                transaction.commit();
                System.out.println(employee);
                id = employee.getId();
                System.out.println("Employee added");
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }

            transaction = session.beginTransaction();
            try {
                Employee employee = session.get(Employee.class, id);
                transaction.commit();
                System.out.println(employee);
                System.out.println("Employee selected");
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        } finally {
            factory.close();
        }
    }
}
