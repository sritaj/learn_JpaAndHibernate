package in.sritaj.jpaandhibernate.entity.inheritancemapping.singletable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Payment Abstract class defining the Table structure and relevant mappings with dependent entities
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "payment_mode", discriminatorType = DiscriminatorType.STRING)
public abstract class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private double amount;

    public Payment(double amount) {
        this.amount = amount;
    }
}
