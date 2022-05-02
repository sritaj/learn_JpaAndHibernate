package in.sritaj.jpaandhibernate.bootstrap;

import com.github.javafaker.Faker;
import in.sritaj.jpaandhibernate.entity.*;
import in.sritaj.jpaandhibernate.enums.Rating;
import in.sritaj.jpaandhibernate.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * DataInitializer class for initializing data for running the Application
 */
@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    PassportRepository passportRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    Faker fs = new Faker();

    public DataInitializer(PersonRepository personRepository, CourseRepository courseRepository, StudentRepository studentRepository, PassportRepository passportRepository, ReviewRepository reviewRepository, EmployeeRepository employeeRepository) {
        this.personRepository = personRepository;
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.passportRepository = passportRepository;
        this.reviewRepository = reviewRepository;
        this.employeeRepository = employeeRepository;
    }

    /**
     * Implemented Command Line Runner interface method for required JDBC data initialization on Application Run
     *
     * @param args - Args required
     */
    @Override
    public void run(String... args) throws Exception {

        //Data Initialization for People class
        Person personFromJPARepo = personRepository.findById(10001);
        System.out.println(personFromJPARepo);

        Person newlyCreatedPerson = personRepository.insert(new Person(fs.name().fullName(), fs.address().cityName(), new Date()));
        System.out.println(newlyCreatedPerson);
        System.out.println(newlyCreatedPerson.getId());

        newlyCreatedPerson.setName(fs.name().fullName());
        String updatedPerson = personRepository.update(newlyCreatedPerson.getId(), newlyCreatedPerson);
        System.out.println(updatedPerson);

        String newlyCreatedPersonWhenIDDoesntExist = personRepository.update(123456, new Person(fs.name().fullName(), fs.address().cityName(), new Date()));
        System.out.println(newlyCreatedPersonWhenIDDoesntExist);

        String personDeleteWhenIDDoesntExist = personRepository.deletePerson(10393);
        System.out.println(personDeleteWhenIDDoesntExist);

        Person newlyCreatedPersonForDelete = personRepository.insert(new Person(fs.name().fullName(), fs.address().cityName(), new Date()));
        String personDeleted = personRepository.deletePerson(newlyCreatedPersonForDelete.getId());
        System.out.println(personDeleted);

        List<Person> allPersons = personRepository.findAll();
        allPersons.forEach(p -> System.out.println(p.getName()));

        //Data Initialization for Course class
        Course course = courseRepository.findById(18999L);
        System.out.println(course);

        Course newCourse = courseRepository.save(new Course(fs.book().title()));
        newCourse.setCourseName(fs.book().title());
        courseRepository.save(newCourse);

        List coursesUsingQuery = courseRepository.fetchAllRecords_basic();
        coursesUsingQuery.forEach(System.out::println);

        System.out.println(" ****** ");

        List<Course> coursesUsingTypedQuery = courseRepository.fetchAllRecords_typedQuery();
        coursesUsingTypedQuery.forEach(System.out::println);

        System.out.println(" ***SQL*** ");

        List coursesUsingSQLQuery = courseRepository.fetchAllRecords_nativeQuery();
        coursesUsingSQLQuery.forEach(System.out::println);

        Course newCourseAdded = courseRepository.save(new Course(fs.book().title()));
        Object actualCourseAdded = courseRepository.fetchRecordBasedOnID_nativeQuery(newCourseAdded.getId());
        System.out.println(actualCourseAdded.toString());

        int recordsAffected = courseRepository.updateRecordBasedOnID_nativeQuery(fs.book().title(), newCourseAdded.getId());
        System.out.println(recordsAffected);

        //Data Initialization for Student class
        Passport passport = new Passport(fs.idNumber().ssnValid());
        Student student = new Student(fs.name().fullName());
        System.out.println(student.getName());
        studentRepository.saveStudentWithPassport(passport, student);

        //Data Initialization for Passport class
        Long passportNo = passport.getId();
        Passport passportDetails = passportRepository.findById(passportNo);
        System.out.println(passportDetails.getStudent().getName());

        //Data Initialization for Review class
        Course courseForReview = new Course(fs.book().title());
        courseRepository.save(courseForReview);
        reviewRepository.createReviewForSpecifiedCourse(courseForReview.getId(), Rating.valueOf("FOUR"), "Good One");

        List<Review> listOfReviews = new ArrayList<>();
        listOfReviews.add(new Review(Rating.valueOf("ONE"), "Bad course"));
        listOfReviews.add(new Review(Rating.valueOf("THREE"), "Good, but more examples are required"));
        listOfReviews.add(new Review(Rating.valueOf("FIVE"), "Kudos!! Well Explained"));
        Review review = new Review(Rating.valueOf("FOUR"), "Just a tad more and it will be perfect");
        listOfReviews.add(review);

        reviewRepository.createMultipleReviewsForSpecifiedCourse(courseForReview.getId(), listOfReviews);

        Course courseFromReview = reviewRepository.retrieveCourseBasedOnTheReview(review.getId());
        System.out.println(courseFromReview.getCourseName());

        //Data Initialization for Student_Course table
        HashMap<String, List<String>> details = studentRepository.retrieveStudentAndCourses(20021L);
        //System.out.println(details.get("sritaj"));
        details.forEach((S, K) -> System.out.println(S + " " + K));

        Student studentWithCourse = new Student(fs.name().fullName());
        Course courseForStudent = new Course(fs.book().title());
        List<Course> courses = new ArrayList<>();
        courses.add(courseForStudent);

        studentRepository.insertStudentAndCourse(studentWithCourse, courseForStudent);

        //Data Initialization for Employee table
        Employee fullTimeEmployee = new FullTimeEmployee("Paul", new BigDecimal(155000));
        Employee partTimeEmployee = new PartTimEmployee("Logan", new BigDecimal(755));

        employeeRepository.insertEmployee(fullTimeEmployee);
        employeeRepository.insertEmployee(partTimeEmployee);

        List<Course> courseWithoutStudents = courseRepository.fetchCoursesWhereStudentsAreNotMapped();
        courseWithoutStudents.forEach(System.out::println);

        System.out.println("Test");

        List<Course> courseWithMinStudents = courseRepository.fetchCoursesInDescendingOrder();
        courseWithMinStudents.forEach(System.out::println);

        List<Student> studentWithPassport = studentRepository.selectStudentWithMatchingPassportPattern("%902%");
        studentWithPassport.forEach(System.out::println);

        HashMap<Object, Object> result = courseRepository.fetchCoursesAndStudentsUsingJoin();
        result.forEach((S, K) -> System.out.println(S + " " + K));

    }
}
