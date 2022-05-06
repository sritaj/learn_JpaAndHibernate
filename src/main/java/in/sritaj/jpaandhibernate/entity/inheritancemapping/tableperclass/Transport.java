package in.sritaj.jpaandhibernate.entity.inheritancemapping.tableperclass;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Transport Entity defining the Table structure and relevant mappings with dependent entities
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Transport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private double amount;

    public Transport(double amount){
        this.amount = amount;
    }


}
