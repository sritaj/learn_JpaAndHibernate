package in.sritaj.jpaandhibernate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Course Entity defining the Table structure and relevant mappings with dependent entities
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@SQLDelete(sql="update course set is_deleted=true where id =?")
@Where(clause = "is_deleted=false")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "course_name", nullable = false)
    private String courseName;

    @Column(name = "created_date")
    @CreationTimestamp
    private LocalDateTime createdDate;

    @OneToMany(mappedBy = "course")
    private List<Review> reviewList = new ArrayList<>();

    @ManyToMany(mappedBy = "courses")
    @JsonIgnore
    private List<Student> students = new ArrayList<>();

    @Column(name = "last_updated_date")
    @UpdateTimestamp
    private LocalDateTime lastUpdatedDate;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    public Course(String courseName) {
        this.courseName = courseName;
    }

    /**
     * Setter to get Reviews for the course
     *
     * @return List - List of Reviews
     */
    public List<Review> getReviewList() {
        return reviewList;
    }

    /**
     * Setter to set Review for the course
     *
     * @param review - Review
     */
    public void setReview(Review review) {
        this.reviewList.add(review);
    }

    /**
     * Setter to remove Review for the course
     *
     * @param review - Review
     */
    public void removeReview(Review review) {
        this.reviewList.remove(review);
    }

    /**
     * Setter to get Students for the course
     *
     * @return List - List of Students
     */
    public List<Student> getStudentList() {
        return students;
    }

    /**
     * Setter to set Student for the course
     *
     * @param student - Student
     */
    public void setStudent(Student student) {
        this.students.add(student);
    }

    /**
     * Setter to remove Student for the course
     *
     * @param student - Student
     */
    public void removeStudent(Student student) {
        this.students.remove(student);
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseName='" + courseName + '\'' +
                '}';
    }
}
