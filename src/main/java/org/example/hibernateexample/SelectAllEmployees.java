package org.example.hibernateexample;

import org.example.hibernateexample.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class SelectAllEmployees {
    public static void main(String[] args) {
        SessionFactory factory = SessionFactoryCreator.getSessionFactory();

        try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            int id = 1;
            try {
                List<Employee> employees  = session.createQuery("from Employee " +
                                "where name = 'Pascal' AND salary > 10000")
                        .getResultList();

                for(Employee employee: employees){
                    System.out.println(employee);
                }

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
