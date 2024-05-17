package org.acme.getting.started;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.employee.model.Employee;
import org.acme.employee.model.EmployeeWorkedHours;
import org.acme.employee.model.Jobs;
import org.acme.jobs.JobsService;
import org.acme.reports.service.WorkedHoursService;
import org.acme.thread.MultithreadingDemo;

import java.text.DateFormat;
import java.text.ParseException;
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

    @Inject
    JobsService serviceJob;

    @Transactional
    @POST
    public Map<String, Object> createEmployeeService(Employee employee) {
        Map<String, Object> result = new HashMap<>();
        service.createEmployee(employee);
        result.put("id",employee.getId());
        result.put("success",true);
        return result;
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
    @Path("/hours/{id}/{fechaini}/{fechaFin}")
    public Map<String, Object> getHoursByEmployeeandDates(String id,String fechaini, String fechaFin) throws ParseException {
        Map<String, Object> hours = new HashMap<>();
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date dateIni = simpleDateFormat.parse(fechaini);
        Date dateFin = simpleDateFormat.parse(fechaFin);
        List<EmployeeWorkedHours> list = serviceWH.getWorkedHoursByEmpId(Long.parseLong(id));
        int totalHors = list.stream().
        filter(c -> c.getWorked_date().after(dateIni)).
                filter(c -> c.getWorked_date().before(dateFin)).
                mapToInt(o -> o.getWorked_hours()).sum();
        hours.put("total_worked_hours",totalHors);
        if (totalHors > 5){
            hours.put("success",true);
        }else{
            hours.put("success",false);
        }


        return hours;

    }

    @GET
    @Path("/payment/{id}/{fechaini}/{fechaFin}")
    public Map<String, Object> getPsymentByEmployeeandDates(String id,String fechaini, String fechaFin) throws ParseException {
        Map<String, Object> hours = new HashMap<>();
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date dateIni = simpleDateFormat.parse(fechaini);
        Date dateFin = simpleDateFormat.parse(fechaFin);
        List<EmployeeWorkedHours> list = serviceWH.getWorkedHoursByEmpId(Long.parseLong(id));
        Employee employee = service.getEmployeeId(Long.parseLong(id));
        Jobs job = serviceJob.getJobId(employee.getJob_id());
        double totalSalary = list.stream().
                filter(c -> c.getWorked_date().after(dateIni)).
                filter(c -> c.getWorked_date().before(dateFin)).
                mapToInt(o -> o.getWorked_hours()).sum() * job.getSalary();
        hours.put("payment",totalSalary);
        if (totalSalary > 0){
            hours.put("success",true);
        }else{
            hours.put("success",false);
        }


        return hours;

    }

    // Main Class
    @GET
    @Path("/multithread/{num}")
    public void getByJobGroupByLastName(Integer num) {

        int n = num; // Number of threads
        for (int i = 0; i < n; i++) {
            MultithreadingDemo object
                    = new MultithreadingDemo();
            object.start();
        }

    }
}
