package com.kvax.recipebookAPI.model.dto;

import com.kvax.recipebookAPI.model.Ingredient;

import lombok.Data;

@Data
public class IngredientDto {
	private Long id;
	private String content;
	
	
	public static IngredientDto from(Ingredient ingredient) {
		IngredientDto ingredientDto = new IngredientDto();
		ingredientDto.setId(ingredient.getId());
		ingredientDto.setContent(ingredient.getContent());
		return ingredientDto;
	}
}
