package com.validation.handle;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.validation.exception.ValidationExceptionDetails;
//essa classe captura a exceção lançada pelo app, recebendo ela no argumento do método
//eu já poderia tratar aqui mesmo e pronto
//mas para trabalhar personalizando minhas exceções vou trabalhar no package exception
@ControllerAdvice
public class RestExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationExceptionDetails> handlerMethodArgumentNotValidException (MethodArgumentNotValidException manve) {
		
		List<FieldError> listFieldErrors = manve.getBindingResult().getFieldErrors();
		String fields = listFieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));
		String fieldsMessage = listFieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));
		
		return new ResponseEntity<>(
                ValidationExceptionDetails.builder() //instanciei o objeto com o builder
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Bad Request Exception, invalid fields")
                .details("Check the field(s) error")
                .developerMessage(manve.getClass().getName())
                .fields(fields)
                .fieldsMessage(fieldsMessage)
                .build(), HttpStatus.BAD_REQUEST);
	}
}
