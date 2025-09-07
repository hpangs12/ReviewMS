package com.reviewms.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reviewms.entity.Reply;
import com.reviewms.entity.Review;
import com.reviewms.exception.ReviewNotFoundException;
import com.reviewms.repository.ReviewRepository;

@Service
public class ReviewService {
	
	@Autowired
	private ReviewRepository reviewRepository;

	public void saveReview(Review review) {
		review.setTimestamp(LocalDateTime.now());
		reviewRepository.save(review);
	}

	public List<Review> getReviewsByProductId(String productId) {
		return reviewRepository.findByProductId(productId);
	}

	public void saveReply(String reviewId, Reply reply) throws Exception {
		Review review = reviewRepository.findById(reviewId)
				.orElseThrow(() -> new ReviewNotFoundException("Review not Found!"));
		reply.setTimestamp(LocalDateTime.now());
		review.getReplies().add(reply);
		reviewRepository.save(review);
	}

	public void removeReview(String reviewId) throws ReviewNotFoundException {
		Review review = reviewRepository.findById(reviewId)
				.orElseThrow(() -> new ReviewNotFoundException("Review not Found!"));
		
		reviewRepository.delete(review);
	}

	public void removeReply(String reviewId, String replyId) throws ReviewNotFoundException {
		Review review = reviewRepository.findById(reviewId)
				.orElseThrow(() -> new ReviewNotFoundException("Review not Found!"));
		List<Reply> replies = review.getReplies().stream().filter(r -> r.getReplyId().equals(replyId)).collect(Collectors.toList());
		if(replies.isEmpty()) throw new ReviewNotFoundException("Reply not found in review!");
		else review.getReplies().remove(replies.get(0));
		reviewRepository.save(review);
	}

	public void removeReviewsByProductId(String productId) {

		reviewRepository.deleteAllByProductId(productId);
		
	}

}
