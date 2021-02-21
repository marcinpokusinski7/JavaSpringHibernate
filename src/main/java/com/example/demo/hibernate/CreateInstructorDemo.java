package com.example.demo.hibernate;

import com.example.demo.entity.Course;
import com.example.demo.entity.Instructor;
import com.example.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateInstructorDemo {
    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();
        try {
            // create object

            Instructor tempInstructor =
                    new Instructor("Susan", "Private", "susan.private@gmail.com");

            InstructorDetail tempInstructorDetail =
                    new InstructorDetail("http://youtube.com/imptirasdz", "Gamer");

            tempInstructor.setInstructorDetail(tempInstructorDetail);
            // start a transaction
            session.beginTransaction();
            // save the instructor
            // This will also save the DETAILS object
            // By Cascadeype.ALL
            System.out.println("Saving instructor: " + tempInstructor);
            session.save(tempInstructor);
            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");

        } finally {

            // add clean up code
            session.close();
            factory.close();
        }
    }
}
