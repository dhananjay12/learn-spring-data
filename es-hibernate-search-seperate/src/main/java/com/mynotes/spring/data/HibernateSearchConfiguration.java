package com.mynotes.spring.data;

import javax.persistence.EntityManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

//@EnableTransactionManagement
//@Configuration
public class HibernateSearchConfiguration {

    @Autowired
    private EntityManager bentityManager;

}