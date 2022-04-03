package com.projeto.appfaculdade.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Problem {
	
	private Integer status;
	private OffsetDateTime time;
	private String title;
	private List<Field> fields;
	
	/**
	 * Classe que distingue os campos dos possíveis erros da API
	 * @author robso
	 *
	 */
	public static class Field {
		
		private String name;
		private String message;
		
		/**
		 * Método construtor
		 * @param name
		 * @param message
		 */
		public Field(String name, String message) {
			super();
			this.name = name;
			this.message = message;
		
		
	}
		
	/**
	 * Getters and Setters
	 * @return
	 */
		
	public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
	}

	/**
	 * Getters and Setters
	 * @return
	 */
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public OffsetDateTime getTime() {
		return time;
	}

	public void setTime(OffsetDateTime time) {
		this.time = time;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Field> getFields() {
		return fields;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

}
