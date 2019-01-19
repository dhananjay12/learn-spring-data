package com.mynotes.spring.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mynotes.spring.data.model.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {

}
