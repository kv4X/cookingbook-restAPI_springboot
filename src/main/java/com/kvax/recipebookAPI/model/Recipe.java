package com.kvax.recipebookAPI.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.kvax.recipebookAPI.model.dto.RecipeDto;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name="recipes")
public class Recipe extends BaseModel{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty(message = "Title is required")
	@Length(min = 10, message = "Description should contain atleast 10 characters")
	private String title;
	@NotEmpty(message = "Description is required")
	@Length(min = 10, message = "Description should contain atleast 10 characters")
	private String description;
	
	@Min(value = 1, message = "Cooking time should not be less than 1")
    @Max(value = 300, message = "Cooking time should not be greater than 300")
	private Integer cookingTime;
	
	@Min(value = 1, message = "Prep time should not be less than 1")
    @Max(value = 300, message = "Prep time should not be greater than 300")
	private Integer prepTime;
	private Boolean published;
	
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "recipe_id")
	private List<Instruction> instructions = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "recipe_id")
	private List<Ingredient> ingredients = new ArrayList<>();

    //private List<Review> reviews;

	
	public static Recipe from(RecipeDto recipeDto) {
		Recipe recipe = new Recipe();
		recipe.setId(recipeDto.getId());
		recipe.setTitle(recipeDto.getTitle());
		recipe.setDescription(recipeDto.getDescription());
		recipe.setCookingTime(recipeDto.getCookingTime());
		recipe.setPrepTime(recipeDto.getPrepTime());
		recipe.setPublished(recipeDto.getPublished());
		return recipe;
	}
}
