package in.sritaj.jpaandhibernate.repository.storedprocedures;

import com.github.javafaker.Faker;
import in.sritaj.jpaandhibernate.entity.storedprocedures.ActionFigures;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

@SpringBootTest
public class ActionFiguresSpringRepositoryTest extends AbstractTestNGSpringContextTests {

    @Autowired
    ActionFiguresSpringDataRepository actionFiguresSpringDataRepository;

    private Faker fs = new Faker();

    @BeforeMethod
    public void dataSetup() {
        int n = 0;
        while (n < 5) {
            actionFiguresSpringDataRepository.save(new ActionFigures(fs.pokemon().name(), fs.pokemon().location(), 91.23));
            n++;
        }
    }

    @Test(testName = "Validate fetching of All Action Figures")
    public void validateSPGetAllActionFigures() {
        List<ActionFigures> shopList = actionFiguresSpringDataRepository.findAllActionFigures();
        System.out.println(shopList.size());
        Assert.assertTrue(shopList.size() == 5);

    }

    @Test(testName = "Validate fetching of All Action Figures By Price")
    public void validateSPGetAllActionFiguresByPrice() {
        actionFiguresSpringDataRepository.save(new ActionFigures(fs.pokemon().name(), fs.pokemon().location(), 112.23));
        actionFiguresSpringDataRepository.save(new ActionFigures(fs.pokemon().name(), fs.pokemon().location(), 112.23));
        List<ActionFigures> shopList = actionFiguresSpringDataRepository.findAllActionFiguresByPrice(103.12);
        System.out.println(shopList.size());
        Assert.assertTrue(shopList.size() == 2);

    }

    @Test(testName = "Validate fetching count of All Action Figures By Price")
    public void validateSPGetAllActionFiguresCountByPrice() {
        actionFiguresSpringDataRepository.save(new ActionFigures(fs.pokemon().name(), fs.pokemon().location(), 112.23));
        List<ActionFigures> shopList = actionFiguresSpringDataRepository.findAllActionFiguresByPrice(103.12);
        System.out.println(shopList.size());
        Assert.assertTrue(shopList.size() == 1);

    }


    @AfterMethod
    public void cleanUp() {
        actionFiguresSpringDataRepository.deleteAll();
    }
}
