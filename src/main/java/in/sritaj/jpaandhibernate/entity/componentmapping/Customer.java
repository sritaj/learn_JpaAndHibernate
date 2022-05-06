package in.sritaj.jpaandhibernate.entity.componentmapping;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Course Customer defining the Table structure and relevant mappings with dependent entities
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    @Embedded
    private CustomerAddress customerAddress;

    public Customer(String name) {
        this.name = name;
    }

    public Customer(String name, CustomerAddress customerAddress) {
        this.name = name;
        this.customerAddress = customerAddress;
    }
}
