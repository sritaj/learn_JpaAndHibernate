package in.sritaj.jpaandhibernate.repository;

import com.github.javafaker.Faker;
import in.sritaj.jpaandhibernate.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

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
}
