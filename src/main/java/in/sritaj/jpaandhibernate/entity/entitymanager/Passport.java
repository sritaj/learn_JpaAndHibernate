package in.sritaj.jpaandhibernate.entity.entitymanager;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Passport Entity defining the Table structure and relevant mappings with dependent entities
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
public class Passport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "passport_id", nullable = false, unique = true)
    private String passportID;

    @OneToOne(mappedBy = "passport")
    private Student student;

    public Passport(String passportID) {
        this.passportID = passportID;
    }

    @Override
    public String toString() {
        return "Passport{" +
                "number='" + passportID + '\'' +
                '}';
    }
}
