package ch.tobiasstaehli.di.scopes;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import ch.tobiasstaehli.di.exceptions.UnableToInstantiateException;

public class SingletonScopeHandler implements ScopeHandler {

	private Map<String, Object> singletons;

	public SingletonScopeHandler() {
		singletons = new HashMap<String, Object>();
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
			if (singletons.containsKey(name)) {
				instance = singletons.get(name);
			} else {
				instance = constructor.newInstance(const_params);
				singletons.put(name, instance);
			}

		} catch (Exception e) {
			throw new UnableToInstantiateException(name, e);
		}
		return instance;
	}
}
