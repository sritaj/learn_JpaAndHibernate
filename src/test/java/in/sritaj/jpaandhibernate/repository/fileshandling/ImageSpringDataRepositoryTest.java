package in.sritaj.jpaandhibernate.repository.fileshandling;

import com.github.javafaker.Faker;
import in.sritaj.jpaandhibernate.entity.fileshandling.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

@SpringBootTest
public class ImageSpringDataRepositoryTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private ImageSpringDataRepository imageSpringDataRepository;

    private Faker fs = new Faker();

    private static final String RESOURCEPATH = System.getProperty("user.dir") + "/test-data/";

    private static final String DOWNLOADPATH = System.getProperty("user.dir") + "/test-download/";

    private Image image;

    private final String fileName = "test.jpg";

    @Test(testName = "Validate Saving of Image")
    public void saveImage() throws IOException {
        imageSpringDataRepository.deleteAll();

        File file = new File(RESOURCEPATH + "test.png");
        byte fileContent[] = new byte[(int) file.length()];
        FileInputStream fis = new FileInputStream(file);
        fis.read(fileContent);

        image = new Image(fileName, fileContent);
        imageSpringDataRepository.save(image);

        Optional<Image> imageSaved = imageSpringDataRepository.findById(image.getId());

        Assert.assertTrue(imageSaved.get().getName().equals(fileName));

        fis.close();
    }

    @Test(testName = "Validate Retrieval of Image", dependsOnMethods = "saveImage")
    public void retrieveImage() throws IOException {

        Optional<Image> imageSaved = imageSpringDataRepository.findById(image.getId());
        File file = new File(DOWNLOADPATH + image.getName());
        FileOutputStream fos = new FileOutputStream(file);

        fos.write(image.getData());

        Assert.assertTrue(file.exists());
        file.delete();
        fos.close();
    }
}
