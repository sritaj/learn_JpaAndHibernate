package in.sritaj.jpaandhibernate.repository.jpqlandnativequeries;

import com.github.javafaker.Faker;
import in.sritaj.jpaandhibernate.entity.jpqlandnativequeries.Author;
import in.sritaj.jpaandhibernate.repository.jpqlandnativequeries.AuthorSpringDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class AuthorSpringDataRepositoryTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private AuthorSpringDataRepository authorSpringDataRepository;

    private Faker fs = new Faker();

    @BeforeMethod
    public void loadAuthorData() {
        int i = 0;
        while (i <= 5) {
            Author author = new Author(fs.name().firstName(), fs.name().lastName(), fs.number().numberBetween(1, 10));
            authorSpringDataRepository.save(author);
            i++;
        }
    }

    @Test(testName = "Validate find all Authors using JPQL Query")
    public void findAllAuthorsUsingJPQLQuery() {

        List<Author> authors = authorSpringDataRepository.findAllAuthors();
        Assert.assertNotNull(authors);
        Assert.assertTrue(authors.size() > 0);
    }

    @Test(testName = "Validate find Authors Partial Data using JPQL Query")
    public void findAllAuthorsPartialDataUsingJPQLQuery() {

        List<Author> authorData = authorSpringDataRepository.findAllAuthorsPartialData();
        Assert.assertNotNull(authorData);
    }

    @Test(testName = "Validate find Authors by firstName using JPQL Query")
    public void findAllAuthorsWithFirstNameUsingJPQLQuery() {
        final String firstName = "S";
        authorSpringDataRepository.save(new Author(firstName, "Patel", 3));
        List<Author> authors = authorSpringDataRepository.findAllAuthorsByFirstName(firstName);

        Assert.assertNotNull(authors);
        Assert.assertEquals(authors.get(0).getFirstName(), firstName);
    }

    @Test(testName = "Validate find Authors by booksPublished using JPQL Query")
    public void findAllAuthorsWithBooksPublishedUsingJPQLQuery() {

        authorSpringDataRepository.save(new Author(fs.name().firstName(), fs.name().lastName(), 14));
        authorSpringDataRepository.save(new Author(fs.name().firstName(), fs.name().lastName(), 22));
        authorSpringDataRepository.save(new Author(fs.name().firstName(), fs.name().lastName(), 24));
        List<Author> authors = authorSpringDataRepository.findAuthorsForGivenPublishedBooks(13, 25);

        Assert.assertNotNull(authors);
        Assert.assertTrue(authors.size() == 3);
    }

    @Test(testName = "Validate deletion of Authors by firstName using JPQL Query")
    public void deleteAuthorsWithFirstNameUsingJPQLQuery() {
        Author author = authorSpringDataRepository.save(new Author("001" + fs.name().firstName(), fs.name().lastName(), 14));
        authorSpringDataRepository.deleteAuthorByfirstName(author.getFirstName());

        List<Author> postDeletion = authorSpringDataRepository.findAllAuthorsByFirstName(author.getFirstName());

        Assert.assertTrue(postDeletion.size() == 0);

    }

    @Test(testName = "Validate find all Authors with Pagination using JPQL Query")
    public void findAllAuthorsWithPaginationUsingJPQLQuery() {

        List<Author> authors = authorSpringDataRepository.findAllAuthors(PageRequest.of(0, 3));

        Assert.assertTrue(authors.size() == 3);

    }

    @Test(testName = "Validate find all Authors with Pagination and Sorting using JPQL Query")
    public void findAllAuthorsWithPaginationAndSortingUsingJPQLQuery() {

        authorSpringDataRepository.deleteAll();
        authorSpringDataRepository.save(new Author("A", fs.name().lastName(), 14));
        authorSpringDataRepository.save(new Author("B", fs.name().lastName(), 22));
        authorSpringDataRepository.save(new Author("C", fs.name().lastName(), 24));
        Iterable<Author> authors = authorSpringDataRepository.findAllAuthors(PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "firstName")));
        List<String> checkAuthor = new ArrayList<>();
        authors.forEach(e -> checkAuthor.add(e.getFirstName()));

        Assert.assertTrue(checkAuthor.size() == 3);
        Assert.assertEquals(checkAuthor.get(0), "C");

    }

    @Test(testName = "Validate find all Authors using Native Query")
    public void findAllAuthorsUsingNativeQuery() {

        List<Author> authors = authorSpringDataRepository.findAllAuthorsNQ();
        Assert.assertNotNull(authors);
        Assert.assertTrue(authors.size() > 0);
    }

    @Test(testName = "Validate find all Authors using Native Query")
    public void findAllAuthorsUsingFirstUsingNativeQuery() {

        final String firstName = "Q";
        authorSpringDataRepository.save(new Author(firstName, "Patel", 3));
        List<Author> authors = authorSpringDataRepository.findAllAuthorsByFirstNameNQ(firstName);

        Assert.assertNotNull(authors);
        Assert.assertEquals(authors.get(0).getFirstName(), firstName);
    }

    @AfterMethod
    public void deleteData() {

        authorSpringDataRepository.deleteAll();
    }

}
