package com.validation.request;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class AnimePostRequestBody {
	
	@NotEmpty(message = "the anime name cannot be empty")
	private String name;
	@NotEmpty(message = "the anime url cannot be empty")
	private String url;
}
