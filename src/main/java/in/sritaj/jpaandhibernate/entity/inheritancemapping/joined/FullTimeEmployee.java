package in.sritaj.jpaandhibernate.entity.inheritancemapping.joined;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;

/**
 * FullTimeEmployee Entity defining the Table structure and relevant mappings with dependent entities
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue(value = "FTE")
public class FullTimeEmployee extends Employee {

    private BigDecimal salary;

    public FullTimeEmployee(String name, BigDecimal salary) {
        super(name);
        this.salary = salary;
    }

}
