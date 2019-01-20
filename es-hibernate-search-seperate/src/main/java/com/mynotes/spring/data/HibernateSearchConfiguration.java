package com.mynotes.spring.data;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

public class HibernateSearchConfiguration {

    @Autowired
    private EntityManager bentityManager;

}