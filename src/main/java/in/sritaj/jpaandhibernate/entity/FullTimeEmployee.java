package in.sritaj.jpaandhibernate.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.math.BigDecimal;

/**
 * FullTimeEmployee Entity defining the Table structure and relevant mappings with dependent entities
 */
@Entity
@Getter
@Setter
public class FullTimeEmployee extends Employee {

    private BigDecimal salary;

    FullTimeEmployee() {
    }

    public FullTimeEmployee(String name, BigDecimal salary) {
        super(name);
        this.salary = salary;
    }

}
