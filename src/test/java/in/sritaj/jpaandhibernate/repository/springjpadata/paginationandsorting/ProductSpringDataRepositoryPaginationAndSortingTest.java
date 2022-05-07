package in.sritaj.jpaandhibernate.repository.springjpadata.paginationandsorting;

import com.github.javafaker.Faker;
import in.sritaj.jpaandhibernate.entity.springjpadata.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ProductSpringDataRepositoryPaginationAndSortingTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private ProductSpringDataRepositoryPaginationAndSorting productSpringDataRepositoryPaginationAndSorting;

    private Faker fs = new Faker();

    @Test(testName = "Validate records are retrieved based on specified page and size parameters")
    public void validatePagingBasedOnPageAndSizeParams() {
        productSpringDataRepositoryPaginationAndSorting.deleteAll();
        int i = 0;
        List<Integer> listOfIds = new ArrayList<>();
        while (i <= 11) {
            Product product = productSpringDataRepositoryPaginationAndSorting.save(new Product(fs.pokemon().name(), fs.pokemon().location(), 25.7));
            listOfIds.add(product.getId());
            System.out.println(product.getName());
            i++;
        }
        int size = 5;
        Page<Product> productsSet1 = productSpringDataRepositoryPaginationAndSorting.findAll(PageRequest.of(0, size));
        productsSet1.forEach(e -> System.out.println(e.getName()));
        Assert.assertTrue(productsSet1.getNumberOfElements() == Long.valueOf(size));

        Page<Product> productsSet2 = productSpringDataRepositoryPaginationAndSorting.findAll(PageRequest.of(2, size));
        productsSet2.forEach(e -> System.out.println(e.getName()));
        Assert.assertFalse(productsSet2.getNumberOfElements() == Long.valueOf(size));
        Assert.assertTrue(productsSet2.getNumberOfElements() == 2);
    }

    @Test(testName = "Validate records are retrieved Sorted in name in Desc order")
    public void validateSortingBasedOnSinglePropertyInDescOrder() {
        productSpringDataRepositoryPaginationAndSorting.deleteAll();

        productSpringDataRepositoryPaginationAndSorting.save(new Product("A", fs.pokemon().location(), 25.7));
        productSpringDataRepositoryPaginationAndSorting.save(new Product("B", fs.pokemon().location(), 25.7));
        productSpringDataRepositoryPaginationAndSorting.save(new Product("C", fs.pokemon().location(), 25.7));

        Iterable<Product> productsSet1 = productSpringDataRepositoryPaginationAndSorting.findAll(Sort.by(Sort.Direction.DESC, "name"));
        List<String> checkSorting = new ArrayList<>();
        productsSet1.forEach(e -> checkSorting.add(e.getName()));

        Assert.assertTrue(checkSorting.get(0).contains("C"));
    }

    @Test(testName = "Validate records are retrieved Sorted in name in Asc order")
    public void validateSortingBasedOnSinglePropertyInAscOrder() {
        productSpringDataRepositoryPaginationAndSorting.deleteAll();

        productSpringDataRepositoryPaginationAndSorting.save(new Product("C", fs.pokemon().location(), 25.7));
        productSpringDataRepositoryPaginationAndSorting.save(new Product("B", fs.pokemon().location(), 25.7));
        productSpringDataRepositoryPaginationAndSorting.save(new Product("A", fs.pokemon().location(), 25.7));

        Iterable<Product> productsSet1 = productSpringDataRepositoryPaginationAndSorting.findAll(Sort.by(Sort.Direction.ASC, "name"));
        List<String> checkSorting = new ArrayList<>();
        productsSet1.forEach(e -> checkSorting.add(e.getName()));

        Assert.assertTrue(checkSorting.get(0).contains("A"));
    }

    @Test(testName = "Validate records are retrieved Sorted in name and price in Asc order")
    public void validateSortingBasedOnMultiplePropertiesInAscOrder() {
        productSpringDataRepositoryPaginationAndSorting.deleteAll();

        productSpringDataRepositoryPaginationAndSorting.save(new Product("A", fs.pokemon().location(), 100.00));
        productSpringDataRepositoryPaginationAndSorting.save(new Product("A", fs.pokemon().location(), 89.99));
        productSpringDataRepositoryPaginationAndSorting.save(new Product("A", fs.pokemon().location(), 77.77));

        Iterable<Product> productsSet1 = productSpringDataRepositoryPaginationAndSorting.findAll(Sort.by(Sort.Direction.ASC, "name", "price"));
        List<Double> checkSorting = new ArrayList<>();
        productsSet1.forEach(e -> checkSorting.add(e.getPrice()));

        Assert.assertTrue(checkSorting.get(0).equals(77.77));
    }

    @Test(testName = "Validate records are retrieved Sorted in name and price in Desc order")
    public void validateSortingBasedOnMultiplePropertiesInDescOrder() {
        productSpringDataRepositoryPaginationAndSorting.deleteAll();

        productSpringDataRepositoryPaginationAndSorting.save(new Product("A", fs.pokemon().location(), 100.00));
        productSpringDataRepositoryPaginationAndSorting.save(new Product("A", fs.pokemon().location(), 89.99));
        productSpringDataRepositoryPaginationAndSorting.save(new Product("A", fs.pokemon().location(), 77.77));

        Iterable<Product> productsSet1 = productSpringDataRepositoryPaginationAndSorting.findAll(Sort.by(Sort.Direction.DESC, "name", "price"));
        List<Double> checkSorting = new ArrayList<>();
        productsSet1.forEach(e -> checkSorting.add(e.getPrice()));
        Assert.assertTrue(checkSorting.get(0).equals(100.00));
    }

    @Test(testName = "Validate records are retrieved based on specified page and size parameters and Sorted in name and price in Desc order")
    public void validatePagingBasedOnPageAndSizeParamsAndSorting() {
        productSpringDataRepositoryPaginationAndSorting.deleteAll();

        productSpringDataRepositoryPaginationAndSorting.save(new Product("A", fs.pokemon().location(), 25.7));
        productSpringDataRepositoryPaginationAndSorting.save(new Product("B", fs.pokemon().location(), 25.7));
        productSpringDataRepositoryPaginationAndSorting.save(new Product("C", fs.pokemon().location(), 25.7));

        Iterable<Product> productsSet1 = productSpringDataRepositoryPaginationAndSorting.findAll(PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "name")));
        List<String> checkSorting = new ArrayList<>();
        productsSet1.forEach(e -> checkSorting.add(e.getName()));

        Assert.assertTrue(checkSorting.get(0).contains("C"));
        Assert.assertTrue(checkSorting.size() == 1);
    }

    @Test(testName = "Validate Search result based on IDs In along with Pagination")
    public void validateFindByUsingInOperatorWithPagination() {
        int i = 0;
        List<Integer> listOfIds = new ArrayList<>();
        while (i <= 5) {
            Product product = productSpringDataRepositoryPaginationAndSorting.save(new Product(fs.pokemon().name(), fs.pokemon().location(), 25.7));
            listOfIds.add(product.getId());
            i++;
        }
        List<Product> product = productSpringDataRepositoryPaginationAndSorting.findByIdIn(listOfIds, Pageable.ofSize(1));

        Assert.assertTrue(product.size() == 1);

    }

}
