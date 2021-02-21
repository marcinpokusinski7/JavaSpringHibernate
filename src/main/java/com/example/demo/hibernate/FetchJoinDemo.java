package com.example.demo.hibernate;

import com.example.demo.entity.Course;
import com.example.demo.entity.Instructor;
import com.example.demo.entity.InstructorDetail;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class FetchJoinDemo {
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
            // start a transaction
            session.beginTransaction();

            // get the instructor from db
            int theId = 1;

            Query<Instructor> query =
                    session.createQuery("select i from Instructor i " +
                                    "JOIN FETCH i.courses " +
                                    "where i.id=:theInstructorId",
                            Instructor.class);

            query.setParameter("theInstructorId", theId);

            // execute query and get instructor

            Instructor tempInstructor = query.getSingleResult();



            session.getTransaction().commit();


            System.out.println("Done!");
            session.close();
            System.out.println("Instructor: " + tempInstructor.getCourses());

        } finally {

            // add clean up code
            session.close();
            factory.close();
        }
    }
}
