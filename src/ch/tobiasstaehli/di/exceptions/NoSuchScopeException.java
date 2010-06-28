package ch.tobiasstaehli.di.exceptions;

public class NoSuchScopeException extends Exception {

	private static final long serialVersionUID = 5095304730269452330L;

	public NoSuchScopeException(String msg) {
		super(
				"The requested Scope '"
						+ msg
						+ "' was not found on this IoCContainer. Maybe you forgot to add it. Use addScope() to add a Scope.");
	}
}
