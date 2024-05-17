package org.acme.thread;


import jakarta.inject.Inject;
import org.acme.employee.model.Employee;
import org.acme.getting.started.EmployeeService;

import java.util.List;

// Java code for thread creation by extending
// the Thread class
public class MultithreadingDemo extends Thread {
    // @Inject
    // EmployeeService employeeService;


    public void run()
    {
        try {
            /*List<Employee> employeeList =  employeeService.getEmployeeAll();
            for (Employee emp: employeeList){
                System.out.println(
                        "Employee " + getName()
                                + " is active");
            }*/
            System.out.println(
                    "Thread " + Thread.currentThread().getId()
                            + " is running");
        }
        catch (Exception e) {
            // Throwing an exception
            System.out.println("Exception is caught");
        }
    }
}

