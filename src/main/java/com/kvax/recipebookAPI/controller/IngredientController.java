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

import com.kvax.recipebookAPI.model.Ingredient;
import com.kvax.recipebookAPI.model.dto.IngredientDto;
import com.kvax.recipebookAPI.service.IngredientService;

@RestController
public class IngredientController {
	private IngredientService ingredientService;
	
	@Autowired
	public IngredientController(IngredientService ingredientService) {
		this.ingredientService = ingredientService;
	}
	
	@GetMapping("api/v1/recipes/{recipeId}/ingredients")
	public ResponseEntity<List<IngredientDto>> getIngredientByRecipe(@PathVariable final Long recipeId){
		List<Ingredient> ingredients = ingredientService.findByRecipeId(recipeId);
		List<IngredientDto> ingredientsDto = ingredients.stream().map(IngredientDto::from).collect(Collectors.toList());
		return ResponseEntity.ok().body(ingredientsDto);
	}

	@PostMapping("api/v1/recipes/{recipeId}/ingredients")
	public ResponseEntity<IngredientDto> addReviewToRecipe(@Valid @PathVariable final Long recipeId, @RequestBody Ingredient ingredient){
		Ingredient found = ingredientService.addIngredientToRecipe(recipeId, ingredient);
		return ResponseEntity.ok().body(IngredientDto.from(found));
	}
	
	@PutMapping("api/v1/recipes/{recipeId}/ingredients/{ingredientId}")
	public ResponseEntity<IngredientDto> editReview(@Valid @PathVariable final Long recipeId, @PathVariable final Long ingredientId, @RequestBody Ingredient ingredient){
		Ingredient edited = ingredientService.editIngredient(recipeId, ingredientId, ingredient);
		return ResponseEntity.ok().body(IngredientDto.from(edited));
	}
	
	@DeleteMapping("api/v1/recipes/{recipeId}/ingredients/{ingredientId}")
	public ResponseEntity<IngredientDto> deleteReviewFromRecipe(@PathVariable final Long recipeId, @PathVariable final Long ingredientId){
		Ingredient deleted = ingredientService.deleteIngredientFromRecipe(recipeId, ingredientId);
		return ResponseEntity.ok().body(IngredientDto.from(deleted));
	}
}
