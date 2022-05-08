package in.sritaj.jpaandhibernate.entity.relationships.onetomany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "phone_number")
public class PhoneNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID;

    @Column(nullable = false)
    private String number;

    private String type;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public PhoneNumber(String number, String type) {
        this.number = number;
        this.type = type;
    }

    public PhoneNumber(String number, String type, Client client) {
        this.number = number;
        this.type = type;
        this.client = client;
    }

}
