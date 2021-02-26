package com.example.demo.hibernate;

import com.example.demo.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetCoursesForUserDemo {
    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();

            // get the student from database
            int studentId = 1;
            Student tempStudent = session.get(Student.class, studentId);
            System.out.println("\n Loaded students");
            System.out.println("Courses " +tempStudent.getCourses());
            // create more courses

            // add student to courses


            session.getTransaction().commit();

            System.out.println("Done!");
        } finally {

            // add clean up code
            session.close();
            factory.close();
        }
    }
}
