package org.acme.getting.started;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Qualifier;
import org.acme.employee.model.Employee;
import org.acme.employee.model.Jobs;
import org.acme.employee.repository.EmployeeRepository;
import org.acme.jobs.repository.JobsRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class EmployeeService {


    @Inject
    EmployeeRepository repository;
    @Inject
    JobsRepository jobRepository;
    public void createEmployee(Employee employee) {

        repository.persist(employee);
    }


    public List<Employee> getEmployeeByJob(String jobName) {
        System.out.println("jobName " + jobName);
        Jobs job = jobRepository.find("name",jobName).firstResult();
        return repository.find("job_id",job.getId()).stream().toList();
    }

    public List<Employee> getEmployeeAll() {

        return repository.findAll().stream().toList();
    }

    public Employee getEmployeeId(Long Id) {

        return repository.findById(Id);
    }

}
