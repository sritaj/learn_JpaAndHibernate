package in.sritaj.jpaandhibernate.repository.mongodb;

import com.github.javafaker.Faker;
import in.sritaj.jpaandhibernate.entity.mongodb.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ItemSpringDataMongoRepositoryTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private ItemSpringDataMongoRepository itemSpringDataMongoRepository;

    private Faker fs = new Faker();

    @Test(testName = "Validate Creation of Item in MongoDB")
    public void validateCreationOfItem() {
        Item item = new Item();
        item.setName(fs.pokemon().name());
        item.setPrice(22.22f);
        Item createdItem = itemSpringDataMongoRepository.save(item);

        Assert.assertNotNull(createdItem);
    }

    @Test(testName = "Validate Retrieval of Item in MongoDB")
    public void validateRetrievalOfItem() {
        String itemName = fs.pokemon().name();
        Item item = new Item();
        item.setName(itemName);
        item.setPrice(22.22f);
        Item createdItem = itemSpringDataMongoRepository.save(item);
        List<Item> items = itemSpringDataMongoRepository.findAll();

        Assert.assertNotNull(createdItem);
        Assert.assertTrue(items.size() == 1);
        Assert.assertTrue(items.get(0).getName().equals(itemName));
    }

    @Test(testName = "Validate Deletion of Item in MongoDB")
    public void validateDeletionOfItem() {
        String itemName = fs.pokemon().name();
        Item item = new Item();
        item.setName(itemName);
        item.setPrice(22.22f);
        itemSpringDataMongoRepository.save(item);
        Optional<Item> createdItem = itemSpringDataMongoRepository.findById(item.getId());

        Assert.assertNotNull(createdItem);
        Assert.assertTrue(createdItem.get().getName().equals(itemName));

        itemSpringDataMongoRepository.deleteById(item.getId());

        Optional<Item> deleteItem = itemSpringDataMongoRepository.findById(item.getId());

        Assert.assertTrue(deleteItem.isEmpty());

    }

    @Test(testName = "Validate Updation of Item in MongoDB")
    public void validateUpdationOfItem() {
        String itemName = fs.pokemon().name();
        Item item = new Item();
        item.setName(itemName);
        item.setPrice(22.22f);
        itemSpringDataMongoRepository.save(item);
        Optional<Item> createdItem = itemSpringDataMongoRepository.findById(item.getId());

        Assert.assertNotNull(createdItem);
        Assert.assertTrue(createdItem.get().getName().equals(itemName));

        String updatedItemName = fs.pokemon().name() + " - Shiny Version";
        item.setName(updatedItemName);
        itemSpringDataMongoRepository.save(item);

        Optional<Item> updatedItem = itemSpringDataMongoRepository.findById(item.getId());

        Assert.assertNotNull(updatedItem);
        Assert.assertTrue(updatedItem.get().getName().equals(updatedItemName));

    }

    @AfterMethod
    public void cleanDB() {
        itemSpringDataMongoRepository.deleteAll();
    }


}
