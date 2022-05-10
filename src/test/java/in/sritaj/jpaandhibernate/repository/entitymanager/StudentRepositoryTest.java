package in.sritaj.jpaandhibernate.repository.entitymanager;

import com.github.javafaker.Faker;
import in.sritaj.jpaandhibernate.entity.entitymanager.Passport;
import in.sritaj.jpaandhibernate.entity.entitymanager.Student;
import in.sritaj.jpaandhibernate.repository.entitymanager.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

@SpringBootTest
public class StudentRepositoryTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private StudentRepository studentRepository;

    private Faker fs = new Faker();

    @Test(testName = "Validate creation of Student based with Passport Details")
    public void validateStudentWithPassportDetails() {
        Passport passport = new Passport(fs.idNumber().ssnValid());
        String extractPassportID = passport.getPassportID();
        Student student = new Student(fs.name().fullName());
        studentRepository.saveStudentWithPassport(passport, student);
        Long studentID = student.getId();

        Student createdStudent = studentRepository.findById(studentID);
        Assert.assertNotNull(createdStudent.getPassport());
        Assert.assertEquals(createdStudent.getPassport().getPassportID(), extractPassportID);
    }
}
