package org.acme.employee.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "employee_worked_hours")
public class EmployeeWorkedHours {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(name ="employee_id")
    private Long employee_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Long employee_id) {
        this.employee_id = employee_id;
    }

    public Integer getWorked_hours() {
        return worked_hours;
    }

    public void setWorked_hours(Integer worked_hours) {
        this.worked_hours = worked_hours;
    }

    public Date getWorked_date() {
        return worked_date;
    }

    public void setWorked_date(Date worked_date) {
        this.worked_date = worked_date;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    @Column(name = "worked_hours")
    private Integer worked_hours;

    @Column(name = "worked_date")
    private Date worked_date;

    @Column(name = "estatus")
    private String estatus;




}
