package com.mynotes.spring.data.employee.persistence;

import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "EMPLOYEE")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "FIRST_NAME")
    private String fname;

    @Column(name ="LAST_NAME")
    private String lname;

    @Column(name ="EMAIL")
    private String email;

    @Column(name ="DOB")
    @Temporal(TemporalType.DATE)
    Calendar dob;

}
