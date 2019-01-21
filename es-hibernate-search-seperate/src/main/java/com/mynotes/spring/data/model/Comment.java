package com.mynotes.spring.data.model;




import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.springframework.data.annotation.Id;

@Indexed(index="comment")
public class Comment {
	
	@Id
    private Long id;

    @Field
    private String title;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
