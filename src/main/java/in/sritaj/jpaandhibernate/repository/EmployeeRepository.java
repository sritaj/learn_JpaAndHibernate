package in.sritaj.jpaandhibernate.repository;

import in.sritaj.jpaandhibernate.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

/**
 * EmployeeRepository class for implementing SQL queries/transactions for Employee Entity using Hibernate and JPA
 */
@Repository
@Transactional
public class EmployeeRepository {

    @Autowired
    EntityManager entityManager;

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
}
