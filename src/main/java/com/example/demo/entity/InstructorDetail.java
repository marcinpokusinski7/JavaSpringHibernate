package com.example.demo.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
// annotate the class as an etity and map to db table
@Data @NoArgsConstructor
@Entity
@Table(name="instructor_detail")
public class InstructorDetail {


    // define the fields
    // annotate the fields with db column names
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "youtube_channel")
    private String youtubeChannel;

    @Column(name = "hobby")
    private String hobby;

    //getters setters also add @OneToOne annotation
    @OneToOne(mappedBy = "instructorDetail", cascade = {CascadeType.DETACH,
            CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}) //Refers to "instructorDetail" property in "Instructor" class
    private Instructor instructor;

    public InstructorDetail(String youtubeChannel, String hobby) {
        this.youtubeChannel = youtubeChannel;
        this.hobby = hobby;
    }

    // generate setter/getter methods @Getter @Setter is build by Data // lombok
    // create constructor @Data generate no arg constructor // lombok
    // generate to String() method @Data generates toString //lombok


    @Override
    public String toString() {
        return "InstructorDetail{" +
                "id=" + id +
                ", youtubeChannel='" + youtubeChannel + '\'' +
                ", hobby='" + hobby + '\'' +
                '}';
    }
}
