package test.pavelyurtaev.compensation.employee.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import test.pavelyurtaev.compensation.employee.model.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {
}
