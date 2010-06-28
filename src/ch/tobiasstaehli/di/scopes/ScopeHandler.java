package ch.tobiasstaehli.di.scopes;

import ch.tobiasstaehli.di.exceptions.UnableToInstantiateException;

public interface ScopeHandler {

	public Object getInstance(String name, Class<?> cls) throws UnableToInstantiateException;
	
}
