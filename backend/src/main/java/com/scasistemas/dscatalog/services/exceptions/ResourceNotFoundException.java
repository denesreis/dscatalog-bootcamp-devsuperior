package com.scasistemas.dscatalog.services.exceptions;

public class ResourceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	//Defenindo o construtor
	public ResourceNotFoundException(String msg) {
		super(msg); //Vai passar variável para o construtor da super classe RuntimeException
	}
}
