package com.kvax.recipebookAPI.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kvax.recipebookAPI.exception.RecordNotFoundException;
import com.kvax.recipebookAPI.model.Review;
import com.kvax.recipebookAPI.repository.RecipeRepository;
import com.kvax.recipebookAPI.repository.ReviewRepository;

@Service
public class ReviewService {
	
	
	private ReviewRepository reviewRepository;
	private RecipeRepository recipeRepository;
	
	@Autowired
	public ReviewService(ReviewRepository reviewRepository, RecipeRepository recipeRepository) {
		this.reviewRepository = reviewRepository;
		this.recipeRepository = recipeRepository;
	}
	
	public List<Review> findByRecipeId(Long id){
		return StreamSupport
				.stream(reviewRepository.findByRecipeId(id).spliterator(), false)
				.collect(Collectors.toList());
	}
	
	public Review addReviewToRecipe(Long recipeId, Review review){
		return recipeRepository.findById(recipeId).map(recipe -> {
            review.setRecipe(recipe);
            return reviewRepository.save(review);
        }).orElseThrow(() -> new RecordNotFoundException("Invalid recipe id: "+recipeId));
	}
	
	public Review deleteReviewFromRecipe(Long recipeId, Long reviewId) {
		return reviewRepository.findByIdAndRecipeId(recipeId, reviewId).map(review -> {
			reviewRepository.delete(review);
			return review;
        }).orElseThrow(() -> new RecordNotFoundException("Invalid recipe id: "+recipeId));
	}
	
	public Review editRecipe(Long recipeId, Long reviewId, Review review) {
		if(!recipeRepository.existsById(recipeId)) {
            throw new RecordNotFoundException("Invalid recipe id: "+recipeId);
        }
		
		return reviewRepository.findByIdAndRecipeId(reviewId, recipeId).map(reviewEdit -> {
			reviewEdit.setMessage(review.getMessage());
			reviewEdit.setStar(review.getStar());
            return reviewRepository.save(reviewEdit);
        }).orElseThrow(() -> new RecordNotFoundException("Recipe id: "+recipeId + " is not owner of review id: "+ reviewId));
	}
}
