package org.acme.reports.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.employee.model.EmployeeWorkedHours;
import org.acme.reports.repository.WordedHoursRepository;

import java.util.List;

@ApplicationScoped
public class WorkedHoursService {

    @Inject
    WordedHoursRepository repository;


    public List<EmployeeWorkedHours> getWorkedHoursByEmpId(Long id) {
        return repository.find("employee_id",id).stream().toList();
    }
}
