package in.sritaj.jpaandhibernate.repository;

import com.github.javafaker.Faker;
import in.sritaj.jpaandhibernate.entity.inheritancemapping.joined.Employee;
import in.sritaj.jpaandhibernate.entity.inheritancemapping.joined.FullTimeEmployee;
import in.sritaj.jpaandhibernate.entity.inheritancemapping.joined.PartTimeEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
public class EmployeeTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private EmployeeRepository employeeRepository;

    private Faker fs = new Faker();

    final private double fteSalary = 45000.23;
    final private double pteSalary = 432.12;

    @Test(testName = "Validate creation of Full Time Employee")
    public void validateCreationOfFTE() {
        employeeRepository.deleteAllRecordsFromEmployee();
        Employee fteEMP = new FullTimeEmployee(fs.funnyName().name(), new BigDecimal(fteSalary));
        employeeRepository.insertEmployee(fteEMP);
        List<BigDecimal> employeeList = employeeRepository.retrieveFullTimeEmployeesSalary();

        Assert.assertTrue(employeeList.size() == 1);
        Assert.assertEquals(employeeList.get(0).toString(), String.valueOf(fteSalary));

    }

    @Test(testName = "Validate creation of Part Time Employee")
    public void validateCreationOfPTE() {
        employeeRepository.deleteAllRecordsFromEmployee();
        Employee pteEMP = new PartTimeEmployee(fs.funnyName().name(), new BigDecimal(pteSalary));
        employeeRepository.insertEmployee(pteEMP);
        List<BigDecimal> employeeList = employeeRepository.retrievePartTimeEmployeesHourlyWage();

        Assert.assertTrue(employeeList.size() == 1);
        Assert.assertEquals(employeeList.get(0).toString(), String.valueOf(pteSalary));

    }

}
