package in.sritaj.jpaandhibernate.entity.storedprocedures;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "actionfigures")
public class ActionFigures {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String name;

    private String description;

    private Double price;

    public ActionFigures(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
