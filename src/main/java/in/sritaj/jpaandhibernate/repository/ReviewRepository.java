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
     * @param ID - specific Course ID
     * @param rating - Rating for the specified course
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
     * @param ID - specific Course ID
     * @param reviewList - List of Reviews
     */
    public void createMultipleReviewsForSpecifiedCourse(Long ID, List<Review> reviewList) {
        Course course = entityManager.find(Course.class, ID);
        for(Review review : reviewList) {
            review.setCourse(course);
            course.setReview(review);
            //new Review(review.getRating(), review.getDescription());
            entityManager.persist(review);
        }

    }
}
