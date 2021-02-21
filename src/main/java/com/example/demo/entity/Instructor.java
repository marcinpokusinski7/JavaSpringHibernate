package com.example.demo.entity;
// annotate the class as an entity and map to db table

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data @NoArgsConstructor
@Entity
@Table(name = "instructor")
public class Instructor {

    // setup mapping instructor -> instructor_detail

    // define the fields
    // annotate the fields with db column names
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "instructor_detail_id")
    private InstructorDetail instructorDetail;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "instructor",
                cascade = {CascadeType.DETACH, CascadeType.PERSIST,
                        CascadeType.MERGE, CascadeType.REFRESH})
    private List<Course> courses;


    public Instructor(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public void add(Course tempCourse){
        if(courses == null){
            courses = new ArrayList<>();
        }
        courses.add(tempCourse);
        tempCourse.setInstructor(this);
    }

    // generate setter/getter methods @Getter @Setter is build by Data // lombok
    // create constructor @Data generate no arg constructor // lombok
    // generate to String() method @Data generates toString //lombok
}
