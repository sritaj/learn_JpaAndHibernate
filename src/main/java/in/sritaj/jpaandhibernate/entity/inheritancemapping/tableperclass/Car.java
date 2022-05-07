package in.sritaj.jpaandhibernate.entity.inheritancemapping.tableperclass;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Car Entity defining the Table structure and relevant mappings with dependent entities
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Car extends Transport {

    @Column(nullable = false)
    private String car;

    public Car(double amount, String car) {
        super(amount);
        this.car = car;
    }

}
