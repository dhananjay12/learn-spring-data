package com.mynotes.spring.data.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.mynotes.spring.data.model.Comment;

public interface CommentRepository extends ElasticsearchRepository<Comment, Long> {

}
