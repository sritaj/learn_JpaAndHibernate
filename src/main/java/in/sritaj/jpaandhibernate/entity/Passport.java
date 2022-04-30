package in.sritaj.jpaandhibernate.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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

    public Passport(String passpordID) {
        this.passportID = passportID;
    }

    @Override
    public String toString() {
        return "Passport{" +
                "number='" + passportID + '\'' +
                '}';
    }
}
