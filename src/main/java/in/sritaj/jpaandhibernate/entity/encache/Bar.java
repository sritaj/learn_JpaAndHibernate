package in.sritaj.jpaandhibernate.entity.encache;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Bar Entity defining the Table structure
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Bar {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String name;

}
