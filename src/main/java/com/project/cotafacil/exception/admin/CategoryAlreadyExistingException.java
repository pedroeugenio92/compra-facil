package com.project.cotafacil.exception.admin;

public class CategoryAlreadyExistingException extends Exception {

	private static final long serialVersionUID = -7848662610303179161L;

	public CategoryAlreadyExistingException(){
		super();
	}
	
	public CategoryAlreadyExistingException(String msg){
		super(msg);
	}
	
	public CategoryAlreadyExistingException(String msg, Throwable cause){
		super(msg, cause);
	}
}
