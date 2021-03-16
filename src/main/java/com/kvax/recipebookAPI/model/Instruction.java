package com.kvax.recipebookAPI.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.kvax.recipebookAPI.model.dto.InstructionDto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name="instructions")
public class Instruction extends BaseModel{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Length(min = 10, max = 1024, message = "Content must be between 10-200 characters. ")
	@Pattern(regexp = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$", message = "Content is invalid.")
	private String content;
	
	@ManyToOne
	private Recipe recipe;
	

	public static Instruction from(InstructionDto instructionDto) {
		Instruction instruction = new Instruction();
		instruction.setId(instructionDto.getId());
		instruction.setContent(instructionDto.getContent());
		return instruction;
	}
	
	
}
