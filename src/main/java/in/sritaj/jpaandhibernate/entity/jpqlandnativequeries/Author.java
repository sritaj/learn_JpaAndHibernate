package in.sritaj.jpaandhibernate.entity.jpqlandnativequeries;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Author Entity defining the Table structure and relevant mappings with dependent entities
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "books_published")
    private int booksPublished;

    public Author(String firstName, String lastName, int booksPublished) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.booksPublished = booksPublished;
    }
}
