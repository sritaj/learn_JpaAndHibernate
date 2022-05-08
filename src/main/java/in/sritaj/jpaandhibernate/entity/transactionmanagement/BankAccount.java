package in.sritaj.jpaandhibernate.entity.transactionmanagement;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * BankAccount Entity defining the Table structure and relevant mappings with dependent entities
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String firstName;

    private String lastName;

    private int bal;

    public BankAccount(String firstName, String lastName, int bal) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.bal = bal;
    }
}
