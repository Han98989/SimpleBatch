package com.michaelcgood;

import org.apache.commons.lang3.builder.ToStringBuilder;
/**
 * Contains the information of a single anime
 *
 * @author Michael C Good michaelcgood.com
 */

public class AnimeDTO {
	
	private Integer id;

	private String cos;
	private String description;
	
	public AnimeDTO(){
		
	}
	
	public AnimeDTO(Integer id, String cos, String description){
		this.id = id;
		this.cos = cos;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCos() {
		return cos;
	}

	public void setCos(String cos) {
		this.cos = cos;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	    public String toString() {
		   return new ToStringBuilder(this)
				   .append("id", this.id)
				   .append("title", this.cos)
				   .append("description", this.description)
				   .toString();
	   }


}
