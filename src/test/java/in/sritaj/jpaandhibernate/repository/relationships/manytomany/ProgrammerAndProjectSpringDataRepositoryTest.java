package in.sritaj.jpaandhibernate.repository.relationships.manytomany;

import com.github.javafaker.Faker;
import in.sritaj.jpaandhibernate.entity.relationships.manytomany.Programmer;
import in.sritaj.jpaandhibernate.entity.relationships.manytomany.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.*;

@SpringBootTest
public class ProgrammerAndProjectSpringDataRepositoryTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private ProgrammerSprintDataRepository programmerSprintDataRepository;

    @Autowired
    private ProjectSpringDataRepository projectSpringDataRepository;

    private Faker fs = new Faker();

    private Project project;
    private Set<Project> projectSet = new HashSet<>();
    private Set<Programmer> programmerSet = new HashSet<>();
    private Programmer programmer;
    private String projectName;
    private String name;

    @BeforeMethod
    public void testdataSetup() {

        name = fs.name().fullName();
        projectName = fs.pokemon().name();
        project = new Project(projectName);
        programmer = new Programmer(name, 272900, projectSet);
        programmerSet.add(programmer);
        programmer.addProject(project, programmerSet);

    }

    @Test(testName = "Validate creation of Programmer and Project")
    public void validateCreationOfProgrammerAndProject() {

        Programmer newlyCreated = programmerSprintDataRepository.save(programmer);

        Assert.assertNotNull(newlyCreated);

    }

    @Test(testName = "Validate retrieval of Programmer and Project")
    public void validateRetrievalOfProgrammerAndCProject() {

        Programmer newlyCreated = programmerSprintDataRepository.save(programmer);

        Optional<Programmer> insertedProgrammer = programmerSprintDataRepository.findById(newlyCreated.getId());
        List<String> result = new ArrayList<>();
        Set<Project> insertedProjects = insertedProgrammer.get().getProjects();
        insertedProjects.forEach(n -> result.add(n.getName()));

        Assert.assertEquals(insertedProgrammer.get().getName(), name);
        Assert.assertEquals(result.get(0), projectName);

    }

    @AfterMethod
    public void cleanDB() {
        programmerSprintDataRepository.deleteAll();
    }
}
