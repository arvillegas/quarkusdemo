package org.acme.employee.repository;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Singleton;
import org.acme.employee.model.Employee;

@ApplicationScoped
public class  EmployeeRepository implements PanacheRepository<Employee> {


}
