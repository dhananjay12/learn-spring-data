package com.mynotes.spring.data.elasticsearch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class SpringDataElasticsearchApplication {	
	

	public static void main(String[] args) {
		SpringApplication.run(SpringDataElasticsearchApplication.class, args);
	}
	
	@Component
	class RibbonRunner implements CommandLineRunner {
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
	
	
}
