package in.sritaj.jpaandhibernate.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToOne
    private Passport passport;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "STUDENT_COURSE", joinColumns = @JoinColumn(name = "STUDENT_ID"), inverseJoinColumns = @JoinColumn(name = "COURSE_ID"))
    List<Course> courses = new ArrayList<>();

    public Student(String name) {
        this.name = name;
    }

    /**
     * Setter to get Courses for the Student
     *
     * @return List - List of Courses
     */
    public List<Course> getCourseList() {
        return courses;
    }

    /**
     * Setter to set Course for the Student
     *
     * @param course - Student
     */
    public void setReview(Course course) {
        this.courses.add(course);
    }

    /**
     * Setter to remove Course for the Student
     *
     * @param course - Student
     */
    public void removeStudent(Course course) {
        this.courses.remove(course);
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                '}';
    }
}
