package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByEmail(String email);

    // define custom query using JPQL with indexed parameters
    @Query("SELECT e FROM Employee e WHERE e.firstName = ?1 and e.lastName = ?2")
    Employee findByJPQL(String firstName, String lastName);

    // define custom query using JPQL with named parameters
    @Query("SELECT e FROM Employee e WHERE e.firstName = :firstName and e.lastName = :lastName")
    Employee findByJPQLNamedParams(@Param("firstName") String firstName, @Param("lastName") String lastName);

    // define custom query using native SQL with index parameters
    @Query(value = "SELECT * FROM employees WHERE first_name = ?1 and last_name = ?2", nativeQuery = true)
    Employee findByNativeSQL(String firstName, String lastName);

    // define custom query using native SQL with named parameters
    @Query(value = "SELECT * FROM employees WHERE first_name = :firstName and last_name = :lastName", nativeQuery = true)
    Employee findByNativeSQLNamedParams(@Param("firstName") String firstName, @Param("lastName") String lastName);

}
