package in.sritaj.jpaandhibernate.entity;

import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

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
