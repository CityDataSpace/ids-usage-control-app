package de.fraunhofer.iese.ids.ucapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class CommonServerException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6322848514142716091L;

	public CommonServerException(String message) {
		super(message);
	}
	
}
