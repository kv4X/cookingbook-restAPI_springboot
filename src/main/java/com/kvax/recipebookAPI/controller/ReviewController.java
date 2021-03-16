package com.kvax.recipebookAPI.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kvax.recipebookAPI.model.Review;
import com.kvax.recipebookAPI.model.dto.ReviewDto;
import com.kvax.recipebookAPI.service.ReviewService;

@RestController
public class ReviewController {
	private ReviewService reviewService;
	
	@Autowired
	public ReviewController(ReviewService reviewService) {
		this.reviewService = reviewService;
	}
	
	@GetMapping("api/v1/recipes/{recipeId}/reviews")
	public ResponseEntity<List<ReviewDto>> getReviewsByRecipe(@PathVariable final Long recipeId){
		List<Review> reviews = reviewService.findByRecipeId(recipeId);
		List<ReviewDto> reviewsDto = reviews.stream().map(ReviewDto::from).collect(Collectors.toList());
		return ResponseEntity.ok().body(reviewsDto);
	}

	@PostMapping("api/v1/recipes/{recipeId}/reviews")
	public ResponseEntity<ReviewDto> addReviewToRecipe(@Valid @PathVariable final Long recipeId, @RequestBody Review review){
		Review reviewFind = reviewService.addReviewToRecipe(recipeId, review);
		return ResponseEntity.ok().body(ReviewDto.from(reviewFind));
	}
	
	@PutMapping("api/v1/recipes/{recipeId}/reviews/{reviewId}")
	public ResponseEntity<ReviewDto> editReview(@Valid @PathVariable final Long recipeId, @PathVariable final Long reviewId, @RequestBody Review review){
		Review reviewEdited = reviewService.editRecipe(recipeId, reviewId, review);
		return ResponseEntity.ok().body(ReviewDto.from(reviewEdited));
	}
	
	@DeleteMapping("api/v1/recipes/{recipeId}/reviews/{reviewId}")
	public ResponseEntity<ReviewDto> deleteReviewFromRecipe(@PathVariable final Long recipeId, @PathVariable final Long reviewId){
		Review review = reviewService.deleteReviewFromRecipe(recipeId, reviewId);
		return ResponseEntity.ok().body(ReviewDto.from(review));
	}
}
