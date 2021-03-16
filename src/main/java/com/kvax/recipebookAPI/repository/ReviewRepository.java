package com.kvax.recipebookAPI.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kvax.recipebookAPI.model.Review;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Long> {
	List<Review> findByRecipeId(Long recipeId);
	Optional<Review> findByIdAndRecipeId(Long id, Long recipeId);
}
