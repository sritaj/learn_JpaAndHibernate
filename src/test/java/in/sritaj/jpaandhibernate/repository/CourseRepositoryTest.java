package in.sritaj.jpaandhibernate.repository;

import com.github.javafaker.Faker;
import in.sritaj.jpaandhibernate.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

@SpringBootTest
public class CourseRepositoryTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private CourseRepository courseRepository;

    private Faker fs = new Faker();

    @Test(testName = "Validate findBy returns the specified course when the ID exists")
    public void validateFindByIDFetchesTheSpecifiedCourse() {
        Course newCourse = courseRepository.save(new Course(fs.book().title()));
        Course course = courseRepository.findById(newCourse.getId());

        Assert.assertNotNull(course);
        Assert.assertEquals(course.getCourseName(), newCourse.getCourseName());

    }

    @Test(testName = "Validate findBy returns the specified course when the ID doesn't exist")
    public void validateFindByIDWhenTheIDDoesntExist() {
        Course course = courseRepository.findById(64738474L);

        Assert.assertNull(course);

    }

    @DirtiesContext
    @Test(testName = "Validate Delete removes the specified course when the ID exists")
    public void validateDeletionOfTheSpecifiedCourse() {
        Course newCourse = courseRepository.save(new Course(fs.book().title()));
        String actualMessage = courseRepository.deleteCourse(newCourse.getId());

        Assert.assertEquals(actualMessage, "Course removed");

    }

    @DirtiesContext
    @Test(testName = "Validate Delete returns the proper error msg when the ID doesn't exist")
    public void validateDeletionWhenTheIDDoesntExist() {
        Long id = 64738474L;
        String actualMessage = courseRepository.deleteCourse(id);

        Assert.assertEquals(actualMessage, "Can't find Course for ID " + id);

    }

    @Test(testName = "Validate Saving new courses")
    public void validateSaveForCreatingNewCourse() {
        String courseName = fs.book().title();
        Course course = courseRepository.save(new Course(courseName));

        Assert.assertNotNull(course);
        Assert.assertEquals(course.getCourseName(), courseName);

    }

    @Test(testName = "Validate Updation of existing courses")
    public void validateSaveForUpdatingExistingCourse() {
        String courseName = fs.book().title();
        String updatedCourseName = fs.book().title();
        Course course = courseRepository.save(new Course(courseName));

        Assert.assertNotNull(course);
        Assert.assertEquals(course.getCourseName(), courseName);

        course.setCourseName(updatedCourseName);
        Course updatedCourse = courseRepository.save(course);

        Assert.assertNotNull(updatedCourse);
        Assert.assertEquals(updatedCourse.getCourseName(), updatedCourseName);

    }

    @Test(testName = "Validate Saving new courses with nullable value", expectedExceptions = DataIntegrityViolationException.class)
    public void validateSaveForCreatingNewCourseWithNullValue() {
        courseRepository.save(new Course(null));

    }

    @Test(testName = "Validate Fetching of Record based on ID")
    public void validateFetchingOnfRecordBasedOnID() {
        String courseName = fs.book().title();
        Course course = courseRepository.save(new Course(courseName));

        Course actualCourseAdded = (Course) courseRepository.fetchRecordBasedOnID_nativeQuery(course.getId());
        Assert.assertEquals(actualCourseAdded.getCourseName(), courseName);

    }

    @Test(testName = "Validate Updation of Course name based on ID")
    public void validateUpdationOfCourseNameBasedOnID() {
        String courseName = fs.book().title();
        Course course = courseRepository.save(new Course(courseName));
        String updatedCourseName = fs.book().title();
        course.setCourseName(updatedCourseName);

        int rowsAffected = courseRepository.updateRecordBasedOnID_nativeQuery(updatedCourseName, course.getId());
        Course savedCourse = courseRepository.findById(course.getId());

        Assert.assertEquals(1, rowsAffected);
        Assert.assertEquals(savedCourse.getCourseName(), updatedCourseName);

    }

    @Test(testName = "Validate Updation of Course name with null value", expectedExceptions = DataIntegrityViolationException.class)
    public void validateUpdationOfCourseNameWithNullValue() {
        String courseName = fs.book().title();
        Course course = courseRepository.save(new Course(courseName));
        String updatedCourseName = null;
        course.setCourseName(updatedCourseName);

        courseRepository.updateRecordBasedOnID_nativeQuery(updatedCourseName, course.getId());

    }

    @DirtiesContext
    @Test(testName = "Validate Soft Delete operation")
    public void validateSoftDeletionOfTheSpecifiedCourse() {
        Course newCourse = courseRepository.save(new Course(fs.book().title()));
        String actualMessage = courseRepository.deleteCourse(newCourse.getId());

        Assert.assertEquals(actualMessage, "Course removed");

        Course course = courseRepository.findById(newCourse.getId());

        Assert.assertNull(course);

    }
}
