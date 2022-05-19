package com.project.cotafacil.exception.client;

public class ClientNotFoundException extends Exception {
	
	public ClientNotFoundException(){
		super();
	}
	
	public ClientNotFoundException(String msg){
		super(msg);
	}
	
	public ClientNotFoundException(String msg, Throwable cause){
		super(msg, cause);
	}
}
