package com.example.demo.hibernate;

import com.example.demo.entity.Instructor;
import com.example.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateDemo {
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
            // create the objects
//            Instructor tempInstructor =
//                    new Instructor("Marcin", "Pokus", "marcinpok@gmail.com");
//
//            InstructorDetail tempInstructorDetail =
//                    new InstructorDetail("Imp3rial89", "Climbing");

            Instructor tempInstructor =
                    new Instructor("Mayhem", "Gane", "mahgen@gmail.com");

            InstructorDetail tempInstructorDetail =
                    new InstructorDetail("CodingInGame", "Spoil");


            // associate the objects
            tempInstructor.setInstructorDetail(tempInstructorDetail);
            // start a transaction
            session.beginTransaction();
            // save the instructor
            // This will also save the DETAILS object
            // By Cascadeype.ALL
            System.out.println("Saving instructor: " +tempInstructor);
            session.save(tempInstructor);
            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");

        }
    }
}
