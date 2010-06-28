package ch.tobiasstaehli.di.exceptions;

public class UnableToInstantiateException extends Exception {

	private static final long serialVersionUID = -2779271881696431996L;

	public UnableToInstantiateException(String name, Exception e) {
		super("A ScopeHandler was not able to create an instance of '" + name
				+ "'\n The nested exception is: " + e.getMessage());
		this.initCause(e);
	}

}
