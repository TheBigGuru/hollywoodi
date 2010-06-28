package ch.tobiasstaehli.di.scopes;

import java.lang.reflect.Constructor;

import ch.tobiasstaehli.di.exceptions.UnableToInstantiateException;

public class PrototypeScopeHandler implements ScopeHandler {

	public PrototypeScopeHandler() {

	}

	@Override
	public Object getInstance(String name, Class<?> cls)
			throws UnableToInstantiateException {
		Object instance = null;
		try {
			
			Object[] const_params = {};
			Class<?>[] const_args = {};
			Constructor<?> constructor = cls.getConstructor(const_args);
			constructor.setAccessible(true); // performance
			instance = constructor.newInstance(const_params);
		} catch (Exception e) {
			e.printStackTrace();
			throw new UnableToInstantiateException(name, e);
		}
		return instance;
	}
}
