package in.sritaj.jpaandhibernate.entity.inheritancemapping.singletable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * CreditCard Entity defining the Table structure and relevant mappings with dependent entities
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("CC")
public class CreditCard extends Payment {

    @Column(name = "card_number", nullable = false)
    private String cardNumber;

    public CreditCard(double amount, String cardNumber) {
        super(amount);
        this.cardNumber = cardNumber;
    }
}
