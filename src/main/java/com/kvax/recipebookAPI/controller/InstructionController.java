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

import com.kvax.recipebookAPI.model.Instruction;
import com.kvax.recipebookAPI.model.dto.InstructionDto;
import com.kvax.recipebookAPI.service.InstructionService;

@RestController
public class InstructionController {
	private InstructionService instructionService;
	
	@Autowired
	public InstructionController(InstructionService instructionService) {
		this.instructionService = instructionService;
	}
	
	@GetMapping("api/v1/recipes/{recipeId}/instructions")
	public ResponseEntity<List<InstructionDto>> getIngredientByRecipe(@PathVariable final Long recipeId){
		List<Instruction> instructions = instructionService.findByRecipeId(recipeId);
		List<InstructionDto> instructionsDto = instructions.stream().map(InstructionDto::from).collect(Collectors.toList());
		return ResponseEntity.ok().body(instructionsDto);	
	}

	@PostMapping("api/v1/recipes/{recipeId}/instructions")
	public ResponseEntity<InstructionDto> addReviewToRecipe(@Valid @PathVariable final Long recipeId, @RequestBody Instruction instruction){
		Instruction found = instructionService.addInstructionToRecipe(recipeId, instruction);
		return ResponseEntity.ok().body(InstructionDto.from(found));
	}
	
	@PutMapping("api/v1/recipes/{recipeId}/instructions/{instructionId}")
	public ResponseEntity<InstructionDto> editReview(@Valid @PathVariable final Long recipeId, @PathVariable final Long instructionId, @RequestBody Instruction instruction){
		Instruction edited = instructionService.editInstruction(recipeId, instructionId, instruction);
		return ResponseEntity.ok().body(InstructionDto.from(edited));
	}
	
	@DeleteMapping("api/v1/recipes/{recipeId}/instructions/{instructionId}")
	public ResponseEntity<InstructionDto> deleteReviewFromRecipe(@PathVariable final Long recipeId, @PathVariable final Long instructionId){
		Instruction deleted = instructionService.deleteInstructionFromRecipe(recipeId, instructionId);
		return ResponseEntity.ok().body(InstructionDto.from(deleted));
	}
}
