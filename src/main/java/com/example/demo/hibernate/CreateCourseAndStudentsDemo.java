package com.example.demo.hibernate;

import com.example.demo.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseAndStudentsDemo {
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

            Course tempCourse = new Course("How to work with Git");

            System.out.println("\n Saving the course");
            session.save(tempCourse);
            System.out.println("Saved the course: " +tempCourse);

            // create student
            Student tempStudent1 = new Student("Endriu", "Golota", "Endriu@gmail.com");
            Student tempStudent2 = new Student("Wlod", "Game", "wlodgamegmail.com");

            tempCourse.addStudent(tempStudent1);
            tempCourse.addStudent(tempStudent2);

            System.out.println("\n saving students...");
            session.save(tempStudent1);
            session.save(tempStudent2);

            session.getTransaction().commit();

            System.out.println("Done!");
        } finally {

            // add clean up code
            session.close();
            factory.close();
        }
    }
}
