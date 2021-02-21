package com.example.demo.hibernate;

import com.example.demo.entity.Instructor;
import com.example.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteDemo {
    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        // create session

        try (factory) {
            Session session = factory.getCurrentSession();

            session.beginTransaction();

            // get instructor by primary key / id
            int theId = 1;
            Instructor tempInstructor =
                    session.get(Instructor.class, theId);

            System.out.println("Found instructor: " +tempInstructor);

            // delete the instructors

            if(tempInstructor != null){
                System.out.println("Deleting: " +tempInstructor);


                // Note: This will also delete details object
                // because of cascadeType.ALL
                session.delete(tempInstructor);
            }
            else{
                System.out.println("Instructor is null");
            }

            session.getTransaction().commit();

            System.out.println("Done!");

        }
    }
}
