package in.sritaj.jpaandhibernate.entity.entitymanager;

import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Address Class for defining Address as embedded data in Student Entity
 */
@Embeddable
@NoArgsConstructor
public class Address {

    public Address(String addressLine, String city) {
        this.addressLine = addressLine;
        this.city = city;
    }

    @Column(name = "address_line")
    private String addressLine;

    private String city;


}
