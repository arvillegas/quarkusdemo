package org.acme.reports.repository;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.employee.model.Employee;
import org.acme.employee.model.EmployeeWorkedHours;

import java.util.List;

@ApplicationScoped
public class WordedHoursRepository implements PanacheRepository<EmployeeWorkedHours> {




}
