package in.sritaj.jpaandhibernate.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "course_name")
    private String courseName;

    Course(){}

    Course(String courseName){
        this.courseName = courseName;
    }
}
