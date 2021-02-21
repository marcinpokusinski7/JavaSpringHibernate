package com.example.demo.hibernate;

import com.example.demo.entity.Course;
import com.example.demo.entity.Instructor;
import com.example.demo.entity.InstructorDetail;
import com.example.demo.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteCourseAndReviewsDemo {
    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();
        try {
            // start a transaction
            session.beginTransaction();

            // get the course
            int theId = 10;
            Course tempCourse = session.get(Course.class, theId);
            // print the course
            System.out.println("Deleting the course: ");//lazy fetch only course
            // print the course reviews
            System.out.println(tempCourse.getReviews()); // lazy fetch course, and reviews
            // delete the course
            session.delete(tempCourse);
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
