package in.sritaj.jpaandhibernate.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Person Entity defining the Table structure and relevant mappings with dependent entities
 */
@Getter
@Setter
@Entity
@NamedQuery(name = "find_all_persons", query = "select p from Person p")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    private String location;
    @Column(name = "birth_date")
    private Date birthdate;

    public Person() {
    }

    //Constructor for Hibernate and JPA implementation
    public Person(String name, String location, Date birthdate) {
        this.name = name;
        this.location = location;
        this.birthdate = birthdate;
    }

    //Constructor for JDBC implementation
    public Person(int id, String name, String location, Date birthdate) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.birthdate = birthdate;
    }

    @Override
    public String toString() {
        return "\n Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", birthdate=" + birthdate +
                '}';
    }
}
