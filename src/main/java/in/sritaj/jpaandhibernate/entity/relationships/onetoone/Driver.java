package in.sritaj.jpaandhibernate.entity.relationships.onetoone;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Driver Entity defining the Table structure and relevant mappings with dependent entities
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private int age;

    @OneToOne(mappedBy = "driver")
    private License license;
}
