package com.kvax.recipebookAPI.model.dto;

import com.kvax.recipebookAPI.model.Review;

import lombok.Data;

@Data
public class ReviewDto {
	private Long id;
	private String message;
	private Integer star;
	
	public static ReviewDto from (Review review) {
		ReviewDto reviewDto = new ReviewDto();
		reviewDto.setId(review.getId());
		reviewDto.setMessage(review.getMessage());
		reviewDto.setStar(review.getStar());
		return reviewDto;
	}
}
