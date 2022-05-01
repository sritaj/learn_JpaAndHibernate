package in.sritaj.jpaandhibernate.entity;

import in.sritaj.jpaandhibernate.enums.Rating;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Rating rating;

    private String description;

    @ManyToOne
    private Course course;

    public Review(Rating rating, String description) {
        this.rating = rating;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Review{" +
                "rating=" + rating +
                ", description='" + description + '\'' +
                '}';
    }
}
