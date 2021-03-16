package com.kvax.recipebookAPI.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kvax.recipebookAPI.model.Instruction;

@Repository
public interface InstructionRepository extends CrudRepository<Instruction, Long>{
	List<Instruction> findByRecipeId(Long recipeId);
	Optional<Instruction> findByIdAndRecipeId(Long id, Long recipeId);
}
