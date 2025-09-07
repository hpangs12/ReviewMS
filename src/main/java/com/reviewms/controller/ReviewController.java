package com.reviewms.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reviewms.entity.Reply;
import com.reviewms.entity.Review;
import com.reviewms.service.ReviewService;

@RestController
@RequestMapping("/review")
public class ReviewController {

	@Autowired
	private ReviewService reviewService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReviewController.class);
	
	@PostMapping
	public ResponseEntity<Review> postReview(@RequestBody Review review){
		reviewService.saveReview(review);
		LOGGER.info(String.format("%s: Review %s saved for product Id %s", review.getTimestamp(), review, review.getProductId()));
		return new ResponseEntity<Review>(review, HttpStatus.CREATED);
	}
	
	@GetMapping("{productId}")
	public ResponseEntity<List<Review>> getReviewsByProductId(@PathVariable String productId){
		return new ResponseEntity<>(reviewService.getReviewsByProductId(productId), HttpStatus.OK);
		
	}
	
	@PostMapping("{reviewId}/reply")
	public ResponseEntity<Reply> postReply(@PathVariable String reviewId, @RequestBody Reply reply) throws Exception{
		reviewService.saveReply(reviewId, reply);
		return new ResponseEntity<Reply>(reply, HttpStatus.CREATED);
	}
	
	@DeleteMapping("{reviewId}")
	public ResponseEntity<?> deleteReview(@PathVariable String reviewId) throws Exception{
		reviewService.removeReview(reviewId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("{reviewId}/{replyId}")
	public ResponseEntity<?> deleteReply(@PathVariable String reviewId, @PathVariable String replyId) throws Exception{
		reviewService.removeReply(reviewId, replyId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
