package in.sritaj.jpaandhibernate.entity.inheritancemapping.tableperclass;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Bike Entity defining the Table structure and relevant mappings with dependent entities
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Bike extends Transport {

    @Column(nullable = false)
    private String bike;

    public Bike(double amount, String bike) {
        super(amount);
        this.bike = bike;
    }
}
