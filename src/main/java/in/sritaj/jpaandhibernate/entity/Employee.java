package in.sritaj.jpaandhibernate.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Employee Interface defining the Table structure and relevant mappings with dependent entities
 */
@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "Employee_Type")
public abstract class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    Employee() {
    }

    Employee(String name) {
        this.name = name;
    }
}
