package in.sritaj.jpaandhibernate.entity.relationships.manytomany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * Project Entity defining the Table structure and relevant mappings with dependent entities
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    @ManyToMany(mappedBy = "projects")
    private Set<Programmer> programmers;

    public Project(String name) {
        this.name = name;
    }

    public Project(String name, Set<Programmer> programmers) {
        this.name = name;
        this.programmers = programmers;
    }
}
