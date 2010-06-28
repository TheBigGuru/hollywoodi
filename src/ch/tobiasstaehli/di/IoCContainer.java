package ch.tobiasstaehli.di;

import ch.tobiasstaehli.di.exceptions.UnableToInstantiateException;
import ch.tobiasstaehli.di.scopes.ScopeHandler;
import ch.tobiasstaehli.di.scopes.ScopeType;

public interface IoCContainer {

	public void addClass(Class<?> cls);

	public void addScope(ScopeType scopeType, ScopeHandler scopeHandler);

	public Object get(String name) throws UnableToInstantiateException;

}
