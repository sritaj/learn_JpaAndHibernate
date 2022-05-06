package in.sritaj.jpaandhibernate.repository;

import in.sritaj.jpaandhibernate.entity.inheritancemapping.joined.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

/**
 * EmployeeRepository class for implementing SQL queries/transactions for Employee Entity using Hibernate and JPA
 */
@Repository
@Transactional
public class EmployeeRepository {

    @Autowired
    private EntityManager entityManager;

    /**
     * Method to insert Employee
     *
     * @param employee - employee entity
     */
    public void insertEmployee(Employee employee) {
        entityManager.persist(employee);
    }

    /**
     * Method to retrieve List Of Employees
     *
     * @return List - list of employees
     */
    public List<Employee> retrieveEmployees() {
        return entityManager.createQuery("Select E from Employee E", Employee.class).getResultList();
    }

    /**
     * Method to retrieve List Of Employees based on Employee Type
     *
     * @param empType - EmpType as String
     * @return List - list of employees
     */
    public List<Employee> retrieveEmployeesBasedOnType(String empType) {
        Query query = entityManager.createNativeQuery("Select * from employee where employee_type = ? ");
        query.setParameter(1, empType);
        List<Employee> employeeList = query.getResultList();
        return employeeList;
    }

    /**
     * Method to retrieve salaries of Full Time Employee
     *
     * @return List - Salaries in BigDecimal format
     */
    public List<BigDecimal> retrieveFullTimeEmployeesSalary() {
        Query query = entityManager.createNativeQuery("Select salary from full_time_employee");
        List<BigDecimal> employeeList = query.getResultList();
        return employeeList;
    }

    /**
     * Method to retrieve Hourly Wages of Part Time Employee
     *
     * @return List - Salaries in BigDecimal format
     */
    public List<BigDecimal> retrievePartTimeEmployeesHourlyWage() {
        Query query = entityManager.createNativeQuery("Select hourly_wage from part_time_employee");
        List<BigDecimal> employeeList = query.getResultList();
        return employeeList;
    }

    /**
     * Method to delete all records from the Employee Table
     */
    public void deleteAllRecordsFromEmployee() {
        Query query = entityManager.createNativeQuery("Delete from employee");
        query.executeUpdate();
    }
}
