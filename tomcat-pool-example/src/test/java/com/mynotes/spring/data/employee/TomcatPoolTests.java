package com.mynotes.spring.data.employee;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;

//@ExtendWith(SpringExtension.class)
//@SpringBootTest(classes=EmployeeServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TomcatPoolTests {

//    @Autowired
//    private DataSource dataSource;
//
//    @Test
//    public void checkTomcat() {
//        assertThat(dataSource.getClass().getName())
//                .isEqualTo("org.apache.tomcat.jdbc.pool.DataSource");
//    }

}
