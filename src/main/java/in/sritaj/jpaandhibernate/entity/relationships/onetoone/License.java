package in.sritaj.jpaandhibernate.entity.relationships.onetoone;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


/**
 * License Entity defining the Table structure and relevant mappings with dependent entities
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class License {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String type;

    @Temporal(TemporalType.DATE)
    private Date valid_from;

    @Temporal(TemporalType.DATE)
    private Date valid_to;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "driver_id")
    private Driver driver;
}
