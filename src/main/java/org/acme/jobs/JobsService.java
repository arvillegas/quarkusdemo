package org.acme.jobs;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.employee.model.Employee;
import org.acme.employee.model.Jobs;
import org.acme.employee.repository.EmployeeRepository;
import org.acme.jobs.repository.JobsRepository;

@ApplicationScoped
public class JobsService {

    @Inject
    JobsRepository repository;
    public void createJob(Jobs job) {

        repository.persist(job);
    }

    public Jobs getJobId(Long Id) {

        return repository.findById(Id);
    }

}
