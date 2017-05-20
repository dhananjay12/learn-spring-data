package com.mynotes.spring.data.elasticsearch;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends ElasticsearchRepository<Article, String> {

	Iterable<Article> findByTitle(String quiery);
	
    @Query("{\"bool\": {\"must\": [{\"match\": {\"tags\": \"?0\"}}]}}")
    Page<Article> findByTagsUsingCustomQuery(String name, Pageable pageable);
}
