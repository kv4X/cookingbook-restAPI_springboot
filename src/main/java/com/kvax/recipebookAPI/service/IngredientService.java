package com.kvax.recipebookAPI.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kvax.recipebookAPI.exception.RecordNotFoundException;
import com.kvax.recipebookAPI.model.Ingredient;
import com.kvax.recipebookAPI.repository.IngredientRepository;
import com.kvax.recipebookAPI.repository.RecipeRepository;

@Service
public class IngredientService {
	private final IngredientRepository ingredientRepository;
	private final RecipeRepository recipeRepository;
	
	@Autowired
	public IngredientService(IngredientRepository ingredientRepository, RecipeRepository recipeRepository) {
		this.ingredientRepository = ingredientRepository;
		this.recipeRepository = recipeRepository;
	}
	
	public List<Ingredient> findByRecipeId(Long id){
		return StreamSupport
				.stream(ingredientRepository.findByRecipeId(id).spliterator(), false)
				.collect(Collectors.toList());
	}
	
	public Ingredient addIngredientToRecipe(Long recipeId, Ingredient ingredient){
		return recipeRepository.findById(recipeId).map(add -> {
			ingredient.setRecipe(add);
            return ingredientRepository.save(ingredient);
        }).orElseThrow(() -> new RecordNotFoundException("Invalid ingredient id: "+recipeId));
	}
	
	public Ingredient deleteIngredientFromRecipe(Long recipeId, Long ingredientId) {
		return ingredientRepository.findByIdAndRecipeId(recipeId, ingredientId).map(edit -> {
			ingredientRepository.delete(edit);
			return edit;
        }).orElseThrow(() -> new RecordNotFoundException("Invalid ingredient id: "+recipeId));
	}
	
	public Ingredient editIngredient(Long recipeId, Long ingredientId, Ingredient ingredient) {
		if(!recipeRepository.existsById(recipeId)) {
            throw new RecordNotFoundException("Invalid ingredient id: "+recipeId);
        }
		
		return ingredientRepository.findByIdAndRecipeId(ingredientId, recipeId).map(edit -> {
			edit.setContent(ingredient.getContent());
            return ingredientRepository.save(edit);
        }).orElseThrow(() -> new RecordNotFoundException("Recipe id: "+recipeId + " is not owner of ingredient id: "+ ingredientId));
	}
}
