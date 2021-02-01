package com.scasistemas.dscatalog.services.exceptions;

public class EntityNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	//Defenindo o construtor
	public EntityNotFoundException(String msg) {
		super(msg); //Vai passar variável para o construtor da super classe RuntimeException
	}
}
