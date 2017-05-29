package com.mynotes.spring.data.elasticsearch;

import java.util.List;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class SpringDataElasticsearchApplication {	
	

	public static void main(String[] args) {
		SpringApplication.run(SpringDataElasticsearchApplication.class, args);
	}
	
	@Component
	class MyCustomRunner1 implements CommandLineRunner {
		@Autowired
	    private ArticleRepository repository;
		

		@Override
		public void run(String... args) throws Exception {
			deleteAll();
			saveQuery();
			System.out.println("####### findByTITLE ##############");
			Iterable<Article> list=repository.findByTitle("spring");
			list.forEach((a)->System.out.println(a));
			System.out.println("####### findBy using custom ##############");
			list=repository.findByTagsUsingCustomQuery("spring", new PageRequest(0, 10));
			list.forEach((a)->System.out.println(a));

		}
		
		
		
		private void deleteAll(){
			repository.deleteAll();
		}
		
		private void  saveQuery(){
			Article article = new Article();
			article.setTitle("Spring Data Basics");
			article.setBody("spring data learning example");
			article.setTags("spring-data spring elasticsearch");
			repository.save(article);
			article = new Article();
			article.setTitle("Elasticsearch setup");
			article.setBody("how to install elasticsearch on ubuntu");
			article.setTags("elasticsearch");
			repository.save(article);
			article = new Article();
			article.setTitle("Java 8 lambda expressions");
			article.setBody("learning the new features in java 8");
			article.setTags("java");
			repository.save(article);
			article = new Article();
			article.setTitle("Spring Boot Basics");
			article.setBody("spring boot learning example");
			article.setTags("spring-boot spring");
			repository.save(article);
			article = new Article();
			article.setTitle("Installing HEAD plugin elasticsearch");
			article.setBody("Installing HEAD plugin elasticsearch");
			article.setTags("elasticsearch head-plugin");
			repository.save(article);
		}

	}
	
	@Component
	class MyCustomRunner2 implements CommandLineRunner {
		@Autowired
	    private ArticleRepository repository;
		
		@Autowired
		private Client client;
		
		@Autowired
		private ElasticsearchOperations elasticsearchOperations;
		
		String query="{\"query\":{\"bool\":{\"must\":[{\"term\":{\"body\":\"spring\"}}],\"must_not\":[],\"should\":[]}},\"from\":0,\"size\":10,\"sort\":[],\"aggs\":{}}";
		

		@Override
		public void run(String... args) throws Exception {
			deleteAll();
			saveQuery();
			System.out.println("####### findBy using native search ##############");
			SearchQuery searchQuery = new NativeSearchQueryBuilder()
					  .withFilter(QueryBuilders.matchQuery("body", ".*spring*"))
					  .build();
					List<Article> articles = elasticsearchOperations.queryForList(searchQuery, Article.class);
					articles.stream().forEach(obj->System.out.println(obj));
			

		}
		
		
		
		private void deleteAll(){
			repository.deleteAll();
		}
		
		private void  saveQuery(){
			Article article = new Article();
			article.setTitle("Spring Data Basics");
			article.setBody("spring data learning example");
			article.setTags("spring-data spring elasticsearch");
			repository.save(article);
			article = new Article();
			article.setTitle("Elasticsearch setup");
			article.setBody("how to install elasticsearch on ubuntu");
			article.setTags("elasticsearch");
			repository.save(article);
			article = new Article();
			article.setTitle("Java 8 lambda expressions");
			article.setBody("learning the new features in java 8");
			article.setTags("java");
			repository.save(article);
			article = new Article();
			article.setTitle("Spring Boot Basics");
			article.setBody("spring boot learning example");
			article.setTags("spring-boot spring");
			repository.save(article);
			article = new Article();
			article.setTitle("Installing HEAD plugin elasticsearch");
			article.setBody("Installing HEAD plugin elasticsearch");
			article.setTags("elasticsearch head-plugin");
			repository.save(article);
		}

	}
	
	
}
