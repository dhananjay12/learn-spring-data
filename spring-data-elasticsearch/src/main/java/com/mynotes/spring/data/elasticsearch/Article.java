package com.mynotes.spring.data.elasticsearch;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.MultiField;


@Document(indexName = "blog", type = "article")
public class Article {
	
	@Id
    private String id;

    @MultiField(mainField = @Field(type = FieldType.String))
    private String title;

    @Field(type = FieldType.String)
    private String body;

    @Field(type = FieldType.String)
    private String tags;
    
    public Article() {
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", body=" + body + ", tags=" + tags + "]";
	}
    
    

}
