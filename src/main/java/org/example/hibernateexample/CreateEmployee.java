package org.example.hibernateexample;

import org.example.hibernateexample.entity.Detail;
import org.example.hibernateexample.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Properties;


public class CreateEmployee {

    public static void main(String[] args) {
        SessionFactory factory = SessionFactoryCreator.getSessionFactory();
        try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                Employee employee = new Employee("Gary", "Nest", "Sales", 7000);
                Detail detail = new Detail("Poltava", "1987654321", "gary.nest@gmail.com");
                employee.setEmpDetails(detail);
                session.save(employee);
                transaction.commit();
                System.out.println(employee);
                System.out.println("Done");
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        } finally {
            factory.close();
        }
    }
}
