package com.scasistemas.dscatalog.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.scasistemas.dscatalog.services.exceptions.DatabaseException;
import com.scasistemas.dscatalog.services.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	
	public ResponseEntity<StandardError> entityNotFound(ResourceNotFoundException e,HttpServletRequest request){ 
		//HttpStatus status = HttpStatus.BAD_REQUEST.value();
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(HttpStatus.NOT_FOUND.value());	//NOT_FOUND é o erro numero 404
		err.setError("Recurso não encontrado");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(err); //.body para retornar o corpo da resposta
	}

	@ExceptionHandler(DatabaseException.class)
	
	public ResponseEntity<StandardError> entityNotFound(DatabaseException e,HttpServletRequest request){ 
		//HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(HttpStatus.BAD_GATEWAY.value());	//NOT_FOUND é o erro numero 400
		err.setError("Exeção de Base de Dados");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_GATEWAY.value()).body(err); //.body para retornar o corpo da resposta
	}	
}
