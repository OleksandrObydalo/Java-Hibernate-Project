package org.example.hibernateexample;

import org.example.hibernateexample.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UpdateEmployee {
    public static void main(String[] args) {
        SessionFactory factory = SessionFactoryCreator.getSessionFactory();

        try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            int id = 1;
            try {
//                Employee employee = session.get(Employee.class, 1);
//                employee.setSalary(15000);

                session.createQuery("update Employee " +
                        "SET salary=30000 where name= 'Pascal' ")
                        .executeUpdate();

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
