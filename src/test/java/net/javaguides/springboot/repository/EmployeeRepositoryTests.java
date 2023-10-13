package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.Employee;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class EmployeeRepositoryTests {

    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee employee;

    @BeforeEach
    public void setup() {
        employee = Employee.builder()
                .firstName("gyuno")
                .lastName("lee")
                .email("gyuno@gmail.com")
                .build();
    }

    // Junit test for save employee operation
    @DisplayName("Junit test for save employee operation")
    @Test
    public void givenEmployeeObject_whenSave_thenReturnSavedEmployee() {

        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("gyuno")
//                .lastName("gyuno")
//                .email("gyuno@gmail.com")
//                .build();

        // when - action or the behavior that we are going test
        Employee savedEmployee = employeeRepository.save(employee);

        // then - verify the output
        Assertions.assertThat(savedEmployee).isNotNull();
        Assertions.assertThat(savedEmployee.getId()).isGreaterThan(0);

    }

    // JUnit test for get all employees operation
    @DisplayName("Junit test for get all employees operation")
    @Test
    public void givenEmployeesList_whenFindAll_thenEmployeesList() {
        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("gyuno")
//                .lastName("lee")
//                .email("gyuno@gmail.com")
//                .build();

        Employee employee1 = Employee.builder()
                .firstName("james")
                .lastName("kim")
                .email("james@gmail.com")
                .build();

        employeeRepository.save(employee);
        employeeRepository.save(employee1);
        // when - action or the behavior that we are going test
        List<Employee> employeeList = employeeRepository.findAll();

        // then - verify the output
        Assertions.assertThat(employeeList).isNotNull();
        Assertions.assertThat(employeeList.size()).isEqualTo(2);

    }

    // JUnit test for get employee by id operation
    @DisplayName("Junit test for get employee by id operation")
    @Test
    public void givenEmployeeObject_whenFindById_thenReturnEmployee() {
        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("gyuno")
//                .lastName("lee")
//                .email("gyuno@gmail.com")
//                .build();
        employeeRepository.save(employee);

        // when - action or the behavior that we are going test
        Employee employeeDB = employeeRepository.findById(employee.getId()).get();

        // then - verify the output
        Assertions.assertThat(employeeDB).isNotNull();
    }

    // JUnit test for get employee by email operation
    @DisplayName("Junit test for get employee by email operation")
    @Test
    public void givenEmployeeEmail_whenFindByEmail_thenReturnEmployeeObject() {
        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("gyuno")
//                .lastName("park")
//                .email("gyuno@gmail.com")
//                .build();
        employeeRepository.save(employee);

        // when - action or the behavior that we are going test
        Employee employeeDB = employeeRepository.findByEmail(employee.getEmail()).get();

        // then - verify the output
        Assertions.assertThat(employeeDB).isNotNull();
    }

    // JUnit test for update employee operation
    @DisplayName("Junit test for update employee operation")
    @Test
    public void givenEmployObject_whenUpdateEmployee_thenReturnUpdatedEmployee() {
        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("gyuno")
//                .lastName("park")
//                .email("gyuno@gmail.com")
//                .build();
        employeeRepository.save(employee);

        // when - action or the behavior that we are going test
        Employee savedEmployee = employeeRepository.findById(employee.getId()).get();
        savedEmployee.setEmail("lee@gmail.com");
        savedEmployee.setFirstName("kim");

        Employee updatedEmployee = employeeRepository.save(savedEmployee);

        // then - verify the output
        Assertions.assertThat(updatedEmployee.getEmail()).isEqualTo("lee@gmail.com");
        Assertions.assertThat(updatedEmployee.getFirstName()).isEqualTo("kim");
    }

    // JUnit test for delete employee operation
    @DisplayName("Junit test for delete employee operation")
    @Test
    public void givenEmployeeObject_whenDelete_thenRemoveEmployee() {
        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("gyuno")
//                .lastName("park")
//                .email("gyuno@gmail.com")
//                .build();
        employeeRepository.save(employee);

        // when - action or the behavior that we are going test
        employeeRepository.deleteById(employee.getId());
        Optional<Employee> employeeOptional = employeeRepository.findById(employee.getId());

        // then - verify the output
        Assertions.assertThat(employeeOptional).isEmpty();
    }

    // JUnit test for custom query using JPQL with index
    @DisplayName("Junit test for custom query using JPQL with index")
    @Test
    public void givenFirstNameAndLastName_whenFindByJPQL_thenReturnEmployeeObject() {
        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("gyuno")
//                .lastName("lee")
//                .email("gyuno@gmail.com")
//                .build();
        employeeRepository.save(employee);

        String firstName = "gyuno";
        String LastName = "lee";

        // when - action or the behavior that we are going test
        Employee savedEmployee = employeeRepository.findByJPQL(firstName, LastName);

        // then - verify the output
        Assertions.assertThat(savedEmployee).isNotNull();
    }

    // JUnit test for custom query using JPQL with named parameters
    @DisplayName("Junit test for custom query using JPQL with named parameters")
    @Test
    public void givenFirstNameAndLastName_whenFindByJPQLNamedParams_thenReturnEmployeeObject() {
        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("gyuno")
//                .lastName("lee")
//                .email("gyuno@gmail.com")
//                .build();
        employeeRepository.save(employee);
        String firstName = "gyuno";
        String LastName = "lee";

        // when - action or the behavior that we are going test
        Employee savedEmployee = employeeRepository.findByJPQLNamedParams(firstName, LastName);

        // then - verify the output
        Assertions.assertThat(savedEmployee).isNotNull();
    }

    // JUnit test for custom query using native query with index
    @DisplayName("Junit test for custom query using native query with index")
    @Test
    public void givenFirstNameAndLastName_whenFindByNativeSQL_thenReturnEmployeeObject() {
        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("gyuno")
//                .lastName("park")
//                .email("gyuno@gmail.com")
//                .build();
        employeeRepository.save(employee);
//        String firstName = "gyuno";
//        String LastName = "park";

        // when - action or the behavior that we are going test
        Employee savedEmployee = employeeRepository
                .findByNativeSQL(employee.getFirstName(), employee.getLastName());

        // then - verify the output
        Assertions.assertThat(savedEmployee).isNotNull();
    }

    // JUnit test for custom query using native query with named parameters
    @DisplayName("Junit test for custom query using native query with named parameters")
    @Test
    public void givenFirstNameAndLastName_whenFindByNativeSQLNamedParams_thenReturnEmployeeObject() {
        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("gyuno")
//                .lastName("park")
//                .email("gyuno@gmail.com")
//                .build();
        employeeRepository.save(employee);
        // when - action or the behavior that we are going test
        Employee savedEmployee = employeeRepository
                .findByNativeSQLNamedParams(employee.getFirstName(), employee.getLastName());

        // then - verify the output
        Assertions.assertThat(savedEmployee).isNotNull();
    }

}
