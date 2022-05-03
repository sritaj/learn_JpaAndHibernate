package in.sritaj.jpaandhibernate.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address {

    public Address() {
    }

    public Address(String addressLine, String city) {
        this.addressLine = addressLine;
        this.city = city;
    }

    @Column(name = "address_line")
    private String addressLine;

    private String city;


}
