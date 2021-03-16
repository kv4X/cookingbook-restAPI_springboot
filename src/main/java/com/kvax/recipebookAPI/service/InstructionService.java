package com.kvax.recipebookAPI.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kvax.recipebookAPI.exception.RecordNotFoundException;
import com.kvax.recipebookAPI.model.Instruction;
import com.kvax.recipebookAPI.repository.InstructionRepository;
import com.kvax.recipebookAPI.repository.RecipeRepository;

@Service
public class InstructionService {
	private final InstructionRepository instructionRepository;
	private final RecipeRepository recipeRepository;
	
	@Autowired
	public InstructionService(InstructionRepository instructionRepository, RecipeRepository recipeRepository) {
		this.instructionRepository = instructionRepository;
		this.recipeRepository = recipeRepository;
	}
	
	public List<Instruction> findByRecipeId(Long id){
		return StreamSupport
				.stream(instructionRepository.findByRecipeId(id).spliterator(), false)
				.collect(Collectors.toList());
	}
	
	public Instruction addInstructionToRecipe(Long recipeId, Instruction instruction){
		return recipeRepository.findById(recipeId).map(add -> {
			instruction.setRecipe(add);
            return instructionRepository.save(instruction);
        }).orElseThrow(() -> new RecordNotFoundException("Invalid instruction id: "+recipeId));
	}
	
	public Instruction deleteInstructionFromRecipe(Long recipeId, Long instructionId) {
		return instructionRepository.findByIdAndRecipeId(recipeId, instructionId).map(edit -> {
			instructionRepository.delete(edit);
			return edit;
        }).orElseThrow(() -> new RecordNotFoundException("Invalid instruction id: "+recipeId));
	}
	
	public Instruction editInstruction(Long recipeId, Long instructionId, Instruction instruction) {
		if(!recipeRepository.existsById(instructionId)) {
            throw new RecordNotFoundException("Invalid instruction id: "+instructionId);
        }
		
		return instructionRepository.findByIdAndRecipeId(instructionId, recipeId).map(edit -> {
			edit.setContent(instruction.getContent());
            return instructionRepository.save(edit);
        }).orElseThrow(() -> new RecordNotFoundException("Recipe id: "+recipeId + " is not owner of instruction id: "+ instructionId));
	}
}
