package in.sritaj.jpaandhibernate.entity.inheritancemapping.singletable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Cheque Entity defining the Table structure and relevant mappings with dependent entities
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("CH")
public class Cheque extends Payment {

    @Column(name = "cheque_number", nullable = false)
    private String chequeNumber;

    public Cheque(double amount, String chequeNumber) {
        super(amount);
        this.chequeNumber = chequeNumber;
    }
}
