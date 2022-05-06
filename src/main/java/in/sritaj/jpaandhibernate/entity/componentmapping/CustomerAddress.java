package in.sritaj.jpaandhibernate.entity.componentmapping;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * CustomerAddress Class for defining Address as embedded data in Customer Entity
 */
@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class CustomerAddress {

    @Column(name = "streetaddress")
    private String streetAddress;

    private String city;

    private String state;

    private String zipcode;

    private String country;

    public CustomerAddress(String streetAddress, String city, String state, String zipcode, String country) {
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.country = country;
    }
}