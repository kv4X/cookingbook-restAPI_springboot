package com.kvax.recipebookAPI.model.dto;

import com.kvax.recipebookAPI.model.Instruction;

import lombok.Data;

@Data
public class InstructionDto {
	private Long id;
	private String content;
	
	
	public static InstructionDto from(Instruction instruction) {
		InstructionDto instructionDto = new InstructionDto();
		instructionDto.setId(instruction.getId());
		instructionDto.setContent(instruction.getContent());
		return instructionDto;
	}
}
