package in.sritaj.jpaandhibernate.entity.relationships.onetomany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Client Entity defining the Table structure and relevant mappings with dependent entities
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<PhoneNumber> phoneNumbers;

    public Client(String name) {
        this.name = name;
    }

    public Client(String name, Set<PhoneNumber> phoneNumbers) {
        this.name = name;
        this.phoneNumbers = phoneNumbers;
    }

    public void addPhoneNumber(PhoneNumber number) {
        if (phoneNumbers == null) {
            phoneNumbers = new HashSet<>();
        }
        number.setClient(this);
        phoneNumbers.add(number);

    }
}
