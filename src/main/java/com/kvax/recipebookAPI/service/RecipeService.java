package com.kvax.recipebookAPI.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kvax.recipebookAPI.exception.RecordNotFoundException;
import com.kvax.recipebookAPI.model.Recipe;
import com.kvax.recipebookAPI.repository.RecipeRepository;

@Service
public class RecipeService {
	private final RecipeRepository recipeRepository;
	
	@Autowired
	public RecipeService(RecipeRepository recipeRepository) {
		this.recipeRepository = recipeRepository;
	}
	
	public List<Recipe> getRecipes(){
		return StreamSupport
				.stream(recipeRepository.findAll().spliterator(), false)
				.collect(Collectors.toList());
	}
	
	public Recipe getRecipe(Long id) {
		return recipeRepository.findById(id).orElseThrow(() ->
		new RecordNotFoundException("Invalid recipe id: "+id));
	}
	
	public Recipe addRecipe(Recipe recipe) {
		return recipeRepository.save(recipe);
	}
	
	public Recipe deleteRecipe(Long id) {
		Recipe recipe = getRecipe(id);
		recipeRepository.deleteById(id);
		return recipe;
	}
	
	public Recipe editRecipe(Long id, Recipe recipe) {
		Recipe recipeEdit = getRecipe(id);
		recipeEdit.setTitle(recipe.getTitle());
		recipeEdit.setDescription(recipe.getDescription());
		recipeEdit.setPrepTime(recipe.getPrepTime());
		recipeEdit.setCookingTime(recipe.getCookingTime());
		recipeEdit.setPublished(recipe.getPublished());
		return recipeRepository.save(recipeEdit);
	}
}
