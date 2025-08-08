package org.example.hibernateexample;

import org.example.hibernateexample.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class DeleteEmployee {
    public static void main(String[] args) {
        SessionFactory factory = SessionFactoryCreator.getSessionFactory();

        try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {

//                Employee employee = session.get(Employee.class, 9);
//                session.delete(employee);

                session.createQuery("delete Employee where name = 'Steve'").executeUpdate();

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
