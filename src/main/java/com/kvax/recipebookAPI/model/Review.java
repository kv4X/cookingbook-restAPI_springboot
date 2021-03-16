package com.kvax.recipebookAPI.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import com.kvax.recipebookAPI.model.dto.ReviewDto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity(name="review")
public class Review extends BaseModel{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Length(min = 10, max = 1024, message = "Message must be between 2-30 characters. ")
	@Pattern(regexp = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$", message = "Message is invalid.")
	private String message;
	@Range(min=1, max=5)
	private Integer star;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "recipe_id")
	private Recipe recipe;
	
	
	public static Review from(ReviewDto reviewDto) {
		Review review = new Review();
		review.setId(reviewDto.getId());
		review.setMessage(reviewDto.getMessage());
		review.setStar(reviewDto.getStar());
		return review;
	}
}
