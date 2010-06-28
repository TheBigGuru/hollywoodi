package ch.tobiasstaehli.di.exceptions;

public class NoSuchInjectableException extends Exception {

	private static final long serialVersionUID = -2991757196414850833L;

	public NoSuchInjectableException(String msg){
		super("The requested Injectable '"+msg+"' was not found in this IoCContainer. Maybe you forgot to add a @Injetable Annotation or you didn't specify the right name.");
	}
}
