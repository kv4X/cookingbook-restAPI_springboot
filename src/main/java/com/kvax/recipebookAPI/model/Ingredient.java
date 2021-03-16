package com.kvax.recipebookAPI.model;

import lombok.Data;
import lombok.EqualsAndHashCode;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.kvax.recipebookAPI.model.dto.IngredientDto;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name="ingredient")
public class Ingredient extends BaseModel{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Length(min = 10, max = 1024, message = "Content must be between 10-200 characters. ")
	@Pattern(regexp = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$", message = "Content is invalid.")
	private String content;
	
	@ManyToOne
	private Recipe recipe;

    //private List<Review> reviews;
	
	public static Ingredient from(IngredientDto ingredientDto) {
		Ingredient ingredient = new Ingredient();
		ingredient.setId(ingredientDto.getId());
		ingredient.setContent(ingredientDto.getContent());
		return ingredient;
	}
}
