package com.reviewms.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.reviewms.entity.Review;

@Repository
public interface ReviewRepository extends MongoRepository<Review, String>{

	List<Review> findByProductId(String productId);

	void deleteAllByProductId(String productId);

}
