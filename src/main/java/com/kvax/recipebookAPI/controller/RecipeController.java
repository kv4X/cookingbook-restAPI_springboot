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

import com.kvax.recipebookAPI.model.Recipe;
import com.kvax.recipebookAPI.model.dto.RecipeDto;
import com.kvax.recipebookAPI.service.RecipeService;

@RestController
//@RequestMapping("api/v1/recipes")
public class RecipeController {
	private RecipeService recipeService;
	@Autowired
	public RecipeController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}
	
	@GetMapping("api/v1/recipes")
	public ResponseEntity<List<RecipeDto>> getRecipes(){
		List<Recipe> recipes = recipeService.getRecipes();
		List<RecipeDto> recipesDto = recipes.stream().map(RecipeDto::from).collect(Collectors.toList());
		return ResponseEntity.ok().body(recipesDto);
	}
	
	@GetMapping("api/v1/recipes/{id}")
	public ResponseEntity<RecipeDto> getRecipe(@PathVariable final Long id){
		Recipe recipe = recipeService.getRecipe(id);
		return ResponseEntity.ok().body(RecipeDto.from(recipe));
	}
	
	@PostMapping("api/v1/recipes")
	public ResponseEntity<RecipeDto> addRecipe(@RequestBody @Valid final RecipeDto recipeDto){
		Recipe recipe = recipeService.addRecipe(Recipe.from(recipeDto));
		return ResponseEntity.ok().body(RecipeDto.from(recipe));
	}
	
	@DeleteMapping("api/v1/recipes/{id}")
	public ResponseEntity<RecipeDto> deleteRecipe(@PathVariable final Long id){
		Recipe recipe = recipeService.deleteRecipe(id);
		return ResponseEntity.ok().body(RecipeDto.from(recipe));
	}
	
	@PutMapping("api/v1/recipes/{id}")
	public ResponseEntity<RecipeDto> editRecipe(@Valid @PathVariable final Long id, @RequestBody RecipeDto recipeDto){
		Recipe recipe = recipeService.editRecipe(id, Recipe.from(recipeDto));
		return ResponseEntity.ok().body(RecipeDto.from(recipe));
	}

}
