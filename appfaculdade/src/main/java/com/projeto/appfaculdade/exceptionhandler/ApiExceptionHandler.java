package com.projeto.appfaculdade.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.projeto.appfaculdade.exception.DomainException;
import com.projeto.appfaculdade.exception.EntityNotFoundException;
import com.projeto.appfaculdade.exceptionhandler.Problem.Field;


@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{
	
	@Autowired
	private MessageSource messageSource;
	
	/**
	 * Método que trata uma Exceção caso a Entidade não seja encontrada
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<Object> handleEntidadeNaoEncontrada(EntityNotFoundException ex, WebRequest request){
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		Problem problem = new Problem();
		problem.setStatus(status.value());
		problem.setTitle(ex.getMessage());
		problem.setTime(OffsetDateTime.now());
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
	
	/**
	 * Método que trata uma Exceção em caso de erro de dominio
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(DomainException.class)
	public ResponseEntity<Object> handleNegocio(DomainException ex, WebRequest request){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		Problem problem = new Problem();
		problem.setStatus(status.value());
		problem.setTitle(ex.getMessage());
		problem.setTime(OffsetDateTime.now());
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
	
	/**
	 * Método que trata uma Exceção de erros de argumentos inválidos
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<Field> fields = new ArrayList<Problem.Field>();
		
		for(ObjectError error : ex.getBindingResult().getAllErrors()){
			
			String name = ((FieldError) error).getField();
			String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			
			fields.add(new Problem.Field(name , message));
		}
		
		Problem problem = new Problem();
		problem.setStatus(status.value());
		problem.setTitle("One or more fields are invalid."
				+ "Please fill in correctly and try again");
		problem.setTime(OffsetDateTime.now());
		problem.setFields(fields);
		
		return super.handleExceptionInternal(ex, problem, headers, status, request);
	}

}
