package in.sritaj.jpaandhibernate.repository.ehcache;

import in.sritaj.jpaandhibernate.entity.encache.Bar;
import in.sritaj.jpaandhibernate.entity.encache.Foo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;

@SpringBootTest
public class EhCacheDataRepositoryTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private FooSpringDataRepository fooSpringDataRepository;

    @Autowired
    private BarSpringDataRepository barSpringDataRepository;

    @Autowired
    private EntityManager entityManager;

    //TODO - Assertion to check Queries are fired single time, currently the output is being verified in console
    @Test(testName = "Validate Queries Fired When Second Level Caching using Eh cache is Enabled")
    public void validateQueriesFiredWhenCachingIsEnabled() {
        Foo foo = new Foo();
        foo.setName("Test caching");
        fooSpringDataRepository.save(foo);
        fooSpringDataRepository.findById(foo.getId()).get();
        fooSpringDataRepository.findById(foo.getId()).get();
        fooSpringDataRepository.findById(foo.getId()).get();

    }

    //TODO - Assertion to check Queries are fired multiple times, currently the output is being verified in console
    @Test(testName = "Validate Queries Fired When Second Level Caching is not Enabled")
    public void validateQueriesFiredWhenCachingIsNotEnabled() {

        Bar bar = new Bar();
        bar.setName("Test caching");
        barSpringDataRepository.save(bar);
        barSpringDataRepository.findById(bar.getId()).get();
        barSpringDataRepository.findById(bar.getId()).get();
        barSpringDataRepository.findById(bar.getId()).get();

    }
}
