package com.example.demo.hibernate;

import com.example.demo.entity.Instructor;
import com.example.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteInstructorDetailDemo {
    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();
        try {

            // start transaction
            session.beginTransaction();

            // get the instructor detail object
            int theId = 3;
            InstructorDetail tempInstructorDetail =
                    session.get(InstructorDetail.class, theId);
            // print instructor detail
            System.out.println("tempInstructorDetail: " + tempInstructorDetail);
            // print the associated (powiazany) instructor
            System.out.println("the associated instructor: " + tempInstructorDetail.getInstructor());
            // delete the instructor detail

            System.out.println("Deleting tempInstructorDetail: " +tempInstructorDetail);

            // remove the assocaited object reference
            // break bi-directional link

            tempInstructorDetail.getInstructor().setInstructorDetail(null);

            session.delete(tempInstructorDetail);

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");


        }catch (Exception exception){
            exception.printStackTrace();
        }finally {
            session.close();
            factory.close();
        }

    }
}
