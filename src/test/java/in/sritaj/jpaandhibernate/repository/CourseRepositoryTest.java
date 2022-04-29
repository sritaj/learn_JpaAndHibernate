package in.sritaj.jpaandhibernate.repository;

import in.sritaj.jpaandhibernate.entity.Course;

import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@SpringBootTest
public class CourseRepositoryTest extends AbstractTestNGSpringContextTests {

    @Autowired
    CourseRepository courseRepository;

    @Test(testName = "Validate findBy returns the specified course when the ID exists")
    public void validateFindByIDFetchesTheSpecifiedCourse(){
        Course course = courseRepository.findById(18999L);

        Assertions.assertNotNull(course);
        Assertions.assertEquals("Geography", course.getCourseName());
    }

    @Test(testName = "Validate findBy returns the specified course when the ID doesn't exist")
    public void validateFindByIDWhenTheIDDoesntExist(){
        Course course = courseRepository.findById(64738474L);

        Assertions.assertNull(course);
    }
}
