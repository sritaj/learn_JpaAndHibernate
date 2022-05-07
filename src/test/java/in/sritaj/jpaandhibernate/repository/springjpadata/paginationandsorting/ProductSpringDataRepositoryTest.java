package in.sritaj.jpaandhibernate.repository.springjpadata.paginationandsorting;

import com.github.javafaker.Faker;
import in.sritaj.jpaandhibernate.entity.springjpadata.Product;
import in.sritaj.jpaandhibernate.repository.springjpadata.findermethods.ProductSpringDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ProductSpringDataRepositoryTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private ProductSpringDataRepository productSpringDataRepository;

    private Faker fs = new Faker();

    @Test(testName = "Validate creation of Product")
    public void validateCreationOfProduct() {
        Product newProduct = productSpringDataRepository.save(new Product(fs.pokemon().name(), fs.pokemon().location(), 20.22));
        Optional<Product> getProduct = productSpringDataRepository.findById(newProduct.getId());

        Assert.assertNotNull(getProduct);
        Assert.assertEquals(getProduct.get().getName(), newProduct.getName());

    }

    @Test(testName = "Validate creation of Product")
    public void validateUpdationOfProduct() {
        Product newProduct = productSpringDataRepository.save(new Product(fs.pokemon().name(), fs.pokemon().location(), 20.22));
        Optional<Product> getProduct = productSpringDataRepository.findById(newProduct.getId());
        String actualProductNamePostUpdate = getProduct.get().getName() + " U";
        newProduct.setName(actualProductNamePostUpdate);
        Product updatedProduct = productSpringDataRepository.save(newProduct);

        Assert.assertNotNull(getProduct);
        Assert.assertEquals(updatedProduct.getName(), actualProductNamePostUpdate);

    }

    @Test(testName = "Validate deletion of Product")
    public void validateDeletionOfProduct() {
        Product newProduct = productSpringDataRepository.save(new Product(fs.pokemon().name(), fs.pokemon().location(), 20.22));
        productSpringDataRepository.delete(newProduct);
        Optional<Product> getProduct = productSpringDataRepository.findById(newProduct.getId());

        Assert.assertTrue(getProduct.isEmpty());
        Assert.assertFalse(productSpringDataRepository.existsById(newProduct.getId()));

    }

    @Test(testName = "Validate Product Count")
    public void validateProductCount() {
        Product newProduct = productSpringDataRepository.save(new Product(fs.pokemon().name(), fs.pokemon().location(), 20.22));
        Long initialCount = productSpringDataRepository.count();
        Assert.assertTrue(initialCount > 1);

        productSpringDataRepository.delete(newProduct);

        Long postDeleteCount = productSpringDataRepository.count();
        Assert.assertTrue(postDeleteCount == (initialCount - 1));

    }

    @Test(testName = "Validate Search result based on Name")
    public void validateFindByName() {
        Product newProduct = productSpringDataRepository.save(new Product(fs.pokemon().name(), fs.pokemon().location(), 20.22));
        List<Product> product = productSpringDataRepository.findByName(newProduct.getName());

        Assert.assertEquals(product.get(0).getName(), newProduct.getName());

    }

    @Test(testName = "Validate Search result based on Name And Description")
    public void validateFindByNameAndDescription() {
        Product newProduct = productSpringDataRepository.save(new Product(fs.pokemon().name(), fs.pokemon().location(), 20.22));
        List<Product> product = productSpringDataRepository.findByNameAndDescription(newProduct.getName(), newProduct.getDescription());

        Assert.assertEquals(product.get(0).getName(), newProduct.getName());
        Assert.assertEquals(product.get(0).getDescription(), newProduct.getDescription());

    }

    @Test(testName = "Validate Search result based on Price Greater Than")
    public void validateFindByPriceGreaterThan() {
        productSpringDataRepository.save(new Product(fs.pokemon().name(), fs.pokemon().location(), 99.22));
        productSpringDataRepository.save(new Product(fs.pokemon().name(), fs.pokemon().location(), 99.52));
        List<Product> product = productSpringDataRepository.findByPriceGreaterThan(99.3);

        Assert.assertEquals(product.get(0).getPrice(), 99.52);

    }

    @Test(testName = "Validate Search result based on Description Containing")
    public void validateFindByDescContaining() {
        productSpringDataRepository.save(new Product(fs.pokemon().name(), "New Gen X", 99.52));
        List<Product> product = productSpringDataRepository.findByDescriptionContains("X");

        Assert.assertNotNull(product);
        Assert.assertTrue(product.get(0).getDescription().contains("X"));

    }

    @Test(testName = "Validate Search result based on Price In Between")
    public void validateFindByPriceInBetween() {
        productSpringDataRepository.save(new Product(fs.pokemon().name(), fs.pokemon().location(), 1575.00));
        productSpringDataRepository.save(new Product(fs.pokemon().name(), fs.pokemon().location(), 1800.00));
        List<Product> product = productSpringDataRepository.findByPriceBetween(1500.00, 1801.00);

        Assert.assertTrue(product.size() == 2);

    }

    @Test(testName = "Validate Search result based on Name matching pattern")
    public void validateFindByUsingNameMatchingPattern() {
        productSpringDataRepository.save(new Product("Honchkrow", fs.pokemon().location(), 71.00));
        List<Product> product = productSpringDataRepository.findByNameLike("%krow%");

        Assert.assertNotNull(product);
        Assert.assertTrue(product.size() == 1);

    }

    @Test(testName = "Validate Search result based on IDs In")
    public void validateFindByUsingInOperator() {
        int i = 0;
        List<Integer> listOfIds = new ArrayList<>();
        while (i <= 5) {
            Product product = productSpringDataRepository.save(new Product(fs.pokemon().name(), fs.pokemon().location(), 25.7));
            listOfIds.add(product.getId());
            i++;
        }
        List<Product> product = productSpringDataRepository.findByIdIn(listOfIds);

        Assert.assertTrue(product.size() == 6);

    }

    /**
     * Code to cleanup DB entries when working with external vendors like MySQL
     */
    @AfterMethod()
    public void clearProductTable() {
        productSpringDataRepository.deleteAll();
    }
}
