package ch.tobiasstaehli.di;

import ch.tobiasstaehli.di.exceptions.UnableToInstantiateException;
import ch.tobiasstaehli.di.scopes.ScopeHandler;
import ch.tobiasstaehli.di.scopes.ScopeType;

public abstract class ComfortableIoCContainer implements IoCContainer {

	@Override
	public abstract void addClass(Class<?> cls);

	@Override
	public abstract void addScope(ScopeType scopeType, ScopeHandler scopeHandler);

	@Override
	public abstract Object get(String name) throws UnableToInstantiateException;

	public <A> A get(String name, Class<A> cls)
			throws UnableToInstantiateException {
		return cls.cast(get(name));
	}

	public <A> A get(Class<A> cls) throws UnableToInstantiateException {
		return get(createName(cls), cls);
	}

	protected String createName(Class<?> cls) {
		String classname = cls.getSimpleName();
		return classname.substring(0, 1).toLowerCase() + classname.substring(1); // lowercase
		// first
		// letter
	}
}
