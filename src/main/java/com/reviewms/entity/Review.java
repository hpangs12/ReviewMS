package com.reviewms.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "reviews")
@Data
@NoArgsConstructor
public class Review {
	@Id
	private String id;
	private String productId;
	private String userId;
	private int rating;
	private String comment;
	private LocalDateTime timestamp;
	private List<Reply> replies = new ArrayList<>();

}
