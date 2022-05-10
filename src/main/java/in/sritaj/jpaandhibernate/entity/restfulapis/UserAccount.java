package in.sritaj.jpaandhibernate.entity.restfulapis;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * UserAccount Entity defining the Table structure
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String userName;

    private String password;

    private int age;

    private String email;

    private String phoneNumber;

    private String gender;

    private String city;

    private String country;

    @OneToOne(mappedBy = "userAccount")
    private Interest interest;
}
