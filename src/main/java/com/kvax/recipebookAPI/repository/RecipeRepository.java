package com.kvax.recipebookAPI.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kvax.recipebookAPI.model.Recipe;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long>{

}
