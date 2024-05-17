package org.acme.getting.started;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.acme.employee.model.Employee;
import org.acme.employee.model.EmployeeWorkedHours;
import org.acme.reports.service.WorkedHoursService;
import org.acme.thread.MultithreadingDemo;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Path("/employee")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmployeeResource
{
    @Inject
    EmployeeService service;

    @Inject
    WorkedHoursService serviceWH;

    @POST
    public void createEmployeeService(Employee employee) {
        service.createEmployee(employee);
    }

    @GET
    @Path("/getByJob/{jobName}")
    public List<Employee> getByJob(String jobName) {
        return service.getEmployeeByJob(jobName);
    }

    @GET
    @Path("/getByJob/{jobName}/sorted")
    public List<Employee> getByJobSortedByLastName(String jobName) {
        List<Employee> listSortered =  service.getEmployeeByJob(jobName);
        return listSortered.stream()
                .sorted(Comparator.comparing(Employee::getLastname))
                .toList();
    }

    @GET
    @Path("/getByJob/{jobName}/group")
    public Map<String, List<Employee>> getByJobGroupByLastName(String jobName) {
        List<Employee> listGroupping =  service.getEmployeeByJob(jobName);
        return listGroupping.stream().collect(Collectors.groupingBy(Employee::getLastname));
    }

    @GET
    @Path("/{id}/{fechaini}/{fechaFin}")
    public Map<String, Object> getHoursByEmployeeandDates(String id,String fechaIni, String fechaFin) {
        Map<String, Object> hours = new HashMap<>();
        List<EmployeeWorkedHours> list = serviceWH.getWorkedHoursByEmpId(Long.parseLong(id));
        int totalHors = list.stream().
        filter(c -> c.getWorked_date().after(new Date(fechaIni))).
                filter(c -> c.getWorked_date().before(new Date(fechaFin))).
                mapToInt(o -> o.getWorked_hours()).sum();
        hours.put("total_worked_hours",totalHors);
        hours.put("success",true);
        return hours;

    }

    // Main Class
    @GET
    @Path("/multithread/{num}/")
    public void getByJobGroupByLastName(Integer num) {

        int n = num; // Number of threads
        for (int i = 0; i < n; i++) {
            MultithreadingDemo object
                    = new MultithreadingDemo();
            object.start();
        }

    }
}
