package de.fraunhofer.iese.ids.ucapp.exception;

public class QueryFailedException extends Exception {

	private static final long serialVersionUID = 7590481207725343901L;

	public QueryFailedException(String message) {
		super(message);
	}

	public QueryFailedException(String message, Throwable cause) {
		super(message, cause);
	}

}
