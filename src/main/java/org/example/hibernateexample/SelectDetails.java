package org.example.hibernateexample;

import org.example.hibernateexample.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class SelectDetails {
    public static void main(String[] args) {
        SessionFactory factory = SessionFactoryCreator.getSessionFactory();

        try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();

            try {
                Employee employee = session.get(Employee.class, 10);
                System.out.println(employee.getEmpDetails());

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
