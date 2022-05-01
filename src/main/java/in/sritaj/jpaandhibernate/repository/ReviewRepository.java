package in.sritaj.jpaandhibernate.repository;

import in.sritaj.jpaandhibernate.entity.Course;
import in.sritaj.jpaandhibernate.entity.Review;
import in.sritaj.jpaandhibernate.enums.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ReviewRepository {

    @Autowired
    EntityManager entityManager;

    private final String queryForFetchingReviewsForSpecifiedCourse =
            "select * from course \n" +
                    "inner join review on course.id = review.course_id\n" +
                    "where course_id = ?";

    /**
     * Method to fetch Passport based on id
     *
     * @param id - specific id
     * @return Passport - specified Passport
     */
    public Review findById(Long id) {
        return entityManager.find(Review.class, id);
    }

    /**
     * Method to create Review for the Specified course
     *
     * @param ID          - specific Course ID
     * @param rating      - Rating for the specified course
     * @param description - Description for the course
     */
    public void createReviewForSpecifiedCourse(Long ID, Rating rating, String description) {
        Course course = entityManager.find(Course.class, ID);
        Review review = new Review(rating, description);
        review.setCourse(course);
        entityManager.persist(review);

    }

    /**
     * Method to create Review for the Specified course
     *
     * @param ID         - specific Course ID
     * @param reviewList - List of Reviews
     */
    public void createMultipleReviewsForSpecifiedCourse(Long ID, List<Review> reviewList) {
        Course course = entityManager.find(Course.class, ID);
        for (Review review : reviewList) {
            review.setCourse(course);
            course.setReview(review);
            //new Review(review.getRating(), review.getDescription());
            entityManager.persist(review);
        }

    }

    //TODO - Method giving failure on Retreieval, SQLNativeQuery returning same review equivalent to no of diff Reviews present
    /**
     * Method to retrive Reviews based on the specified Course id
     *
     * @param ID - specific Course ID
     * @return List - List of reviews
     */
    public List<Review> retrieveReviewsForSpecifiedCourse(Long ID) {
        Course course = entityManager.find(Course.class, ID);
        return course.getReviewList();
    }

    /**
     * Method to retrive Course based on the Review id
     *
     * @param ID - specific Review ID
     * @return Course - Matching Course
     */
    public Course retrieveCourseBasedOnTheReview(Long ID) {
        Review review = entityManager.find(Review.class, ID);
        return review.getCourse();
    }
}
