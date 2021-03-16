package com.kvax.recipebookAPI.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kvax.recipebookAPI.model.Ingredient;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, Long>{
	List<Ingredient> findByRecipeId(Long recipeId);
	Optional<Ingredient> findByIdAndRecipeId(Long id, Long recipeId);
}
