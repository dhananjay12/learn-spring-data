package com.mynotes.spring.data.advancequeries.querydsl;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long   id;

    private String fname;

    private String lname;

    private int    age;
    private double salary;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(final String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(final String lname) {
        this.lname = lname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(final int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(final double salary) {
        this.salary = salary;
    }

}
