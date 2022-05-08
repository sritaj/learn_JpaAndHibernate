package in.sritaj.jpaandhibernate.entity.relationships.manytomany;

import in.sritaj.jpaandhibernate.entity.relationships.onetomany.PhoneNumber;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Programmer Entity defining the Table structure and relevant mappings with dependent entities
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Programmer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    private int salary;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "programmers_projects",
            joinColumns = @JoinColumn(name = "programmer_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id"))
    private Set<Project> projects;

    public Programmer(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }

    public Programmer(String name, int salary, Set<Project> projects) {
        this.name = name;
        this.salary = salary;
        this.projects = projects;
    }

    /**
     * Method to add Project
     *
     * @param project - project entity
     * @param programmerSet - Programmer Entities Set
     */
    public void addProject(Project project, Set<Programmer> programmerSet) {
        if (projects == null) {
            projects = new HashSet<>();
        }
        project.setProgrammers(programmerSet);

        projects.add(project);

    }
}
