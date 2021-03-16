package com.kvax.recipebookAPI.model.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.kvax.recipebookAPI.model.Recipe;

import lombok.Data;

@Data
public class RecipeDto {
	private Long id;
	private String title;
	private String description;
	private Integer cookingTime;
	private Integer prepTime;
	private Boolean published;
	
	//private List<Review> reviews = new ArrayList<>();
	
	//private List<Ingredient> ingredients = new ArrayList<>();
	
	private List<InstructionDto> instructions = new ArrayList<>();
	private List<IngredientDto> ingredients = new ArrayList<>();

	public static RecipeDto from(Recipe recipe) {
		RecipeDto recipeDto = new RecipeDto();
		recipeDto.setId(recipe.getId());
		recipeDto.setTitle(recipe.getTitle());
		recipeDto.setDescription(recipe.getDescription());
		recipeDto.setCookingTime(recipe.getCookingTime());
		recipeDto.setPrepTime(recipe.getPrepTime());
		recipeDto.setPublished(recipe.getPublished());
		recipeDto.setInstructions(recipe.getInstructions().stream().map(InstructionDto::from).collect(Collectors.toList()));
		recipeDto.setIngredients(recipe.getIngredients().stream().map(IngredientDto::from).collect(Collectors.toList()));

		return recipeDto;
	}
}
