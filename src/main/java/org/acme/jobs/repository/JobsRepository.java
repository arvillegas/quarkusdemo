package org.acme.jobs.repository;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Singleton;
import org.acme.employee.model.Jobs;

@ApplicationScoped
public class JobsRepository implements PanacheRepository<Jobs> {


}
