package in.sritaj.jpaandhibernate.repository;

import in.sritaj.jpaandhibernate.entity.Course;

import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
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

    @Test(testName = "Validate delete removes the specified course when the ID exists")
    public void validateDeletionOfTheSpecifiedCourse(){
        String actualMessage =  courseRepository.deleteCourse(18999L);

        Assertions.assertEquals("Course removed",actualMessage);
    }

    @DirtiesContext
    @Test(testName = "Validate delete returns the proper error msg when the ID doesn't exist")
    public void validateDeletionWhenTheIDDoesntExist(){
        Long id = 64738474L;
        String actualMessage  = courseRepository.deleteCourse(id);

        Assertions.assertEquals("Can't find Course for ID " + id ,actualMessage);
    }

    @Test(testName = "Validate Saving new courses")
    public void validateSaveForCreatingNewCourse(){
        String courseName = "Ikigai";
        Course course = courseRepository.save(new Course(courseName));

        Assertions.assertNotNull(course);
        Assertions.assertEquals(courseName, course.getCourseName());
    }

    @Test(testName = "Validate Updation of existing courses")
    public void validateSaveForUpdatingExistingCourse(){
        String courseName = "Civil";
        String updatedCourseName = "Civil Lessons";
        Course course = courseRepository.save(new Course(courseName));

        Assertions.assertNotNull(course);
        Assertions.assertEquals(courseName, course.getCourseName());

        course.setCourseName(updatedCourseName);
        Course updatedCourse = courseRepository.save(course);

        Assertions.assertNotNull(updatedCourse);
        Assertions.assertEquals(updatedCourseName, updatedCourse.getCourseName());

    }
}
