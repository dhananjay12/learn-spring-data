package com.mynotes.spring.data;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.hibernate.search.jpa.Search;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

//@Component
public class BuildSearchIndex implements ApplicationListener<ApplicationReadyEvent> {
    private static final Logger log = LoggerFactory.getLogger(BuildSearchIndex.class);

    private final EntityManager entityManager;

    @Autowired
    public BuildSearchIndex(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        try {
            Search.getFullTextEntityManager(entityManager)
                .createIndexer()
                .startAndWait();
        } catch (InterruptedException e) {
            log.error("An error occurred when trying to build the search index: ", e);
        }
    }

}
