package in.sritaj.jpaandhibernate.repository.entitymanager;

import com.github.javafaker.Faker;
import in.sritaj.jpaandhibernate.entity.entitymanager.Course;
import in.sritaj.jpaandhibernate.repository.entitymanager.CourseSpringDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class CourseSpringDataRepositoryTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private CourseSpringDataRepository courseSpringDataRepository;

    private Faker fs = new Faker();

    @Test(testName = "Validate findBy response when the Course is Present")
    public void validateFindByIDResponseWhenCourseIsPresent() {
        Course newCourse = courseSpringDataRepository.save(new Course(fs.book().title()));
        Optional<Course> courseOptional = courseSpringDataRepository.findById(newCourse.getId());
        Assert.assertTrue(courseOptional.isPresent());
    }

    @Test(testName = "Validate findBy response when the Course is Not Present")
    public void validateFindByIDResponseWhenCourseIsNotPresent() {
        Optional<Course> courseOptional = courseSpringDataRepository.findById(422999L);
        Assert.assertFalse(courseOptional.isPresent());
    }

    @Test(testName = "Validate Saving new courses")
    public void validateSaveForCreatingNewCourse() {
        String courseName = fs.book().title();
        Course course = new Course(courseName);
        courseSpringDataRepository.save(course);

        Optional<Course> courseOptional = courseSpringDataRepository.findById(course.getId());
        Assert.assertNotNull(courseOptional);
        Assert.assertEquals(courseOptional.get().getCourseName(), courseName);

    }

    @Test(testName = "Validate Updation of existing courses")
    public void validateSaveForUpdatingExistingCourse() {
        String courseName = fs.book().title();
        String updatedCourseName = fs.book().title();
        Course course = new Course(courseName);
        courseSpringDataRepository.save(course);

        Optional<Course> courseOptional = courseSpringDataRepository.findById(course.getId());

        Assert.assertNotNull(courseOptional);
        Assert.assertEquals(courseOptional.get().getCourseName(), courseName);

        course.setCourseName(updatedCourseName);
        courseSpringDataRepository.save(course);

        Optional<Course> courseOptionalPostUpdate = courseSpringDataRepository.findById(course.getId());

        Assert.assertNotNull(courseOptionalPostUpdate);
        Assert.assertEquals(courseOptionalPostUpdate.get().getCourseName(), updatedCourseName);

    }

    @Test(testName = "Validate List of Courses And Sorting")
    public void validateListOfCoursesAndSorting() {
        List<Course> courses = courseSpringDataRepository.findAll(Sort.by(Sort.Direction.ASC, "courseName"));
        courses.forEach(c -> System.out.println(c.getCourseName()));

        Assert.assertTrue(courses.size() > 2);
        Assert.assertTrue(courses.get(0).getCourseName().equals("1Civil Science"));

    }

    @Test(testName = "Validate List of Courses And Sorting")
    public void validatePagination() {
        List<Course> courses = courseSpringDataRepository.findAll();
        PageRequest pageRequest = PageRequest.of(0, 3);
        Page<Course> firstPage = courseSpringDataRepository.findAll(pageRequest);
        firstPage.forEach(c -> System.out.println(c.getCourseName()));

        Assert.assertTrue(firstPage.getSize() == 3);
        Assert.assertEquals(firstPage.getTotalElements(), courses.size());

    }


    @Test(testName = "Validate fetching of Courses based on name")
    public void validateFindByName() {
        Course newCourse = courseSpringDataRepository.save(new Course("1Civil Science"));
        List<Course> listOfCourses = courseSpringDataRepository.findByName(newCourse.getCourseName());

        Assert.assertNotNull(listOfCourses);
        Assert.assertEquals(listOfCourses.get(0).getId(), newCourse.getId());
    }

}
