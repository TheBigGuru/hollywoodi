package ch.tobiasstaehli.di;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import ch.tobiasstaehli.di.annotations.Injectable;
import ch.tobiasstaehli.di.annotations.RequestInjection;
import ch.tobiasstaehli.di.annotations.Scope;
import ch.tobiasstaehli.di.exceptions.NoSuchInjectableException;
import ch.tobiasstaehli.di.exceptions.NoSuchScopeException;
import ch.tobiasstaehli.di.exceptions.UnableToInstantiateException;
import ch.tobiasstaehli.di.scopes.PrototypeScopeHandler;
import ch.tobiasstaehli.di.scopes.ScopeHandler;
import ch.tobiasstaehli.di.scopes.ScopeType;

public class SimpleIoCContainer extends ComfortableIoCContainer {

	protected List<Class<?>> classes;

	protected Map<String, Class<?>> injectableClasses;

	protected Map<ScopeType, ScopeHandler> scopes;

	public SimpleIoCContainer() {
		this.classes = new ArrayList<Class<?>>();
		injectableClasses = new HashMap<String, Class<?>>();
		scopes = new HashMap<ScopeType, ScopeHandler>();
		addScope(ScopeType.PROTOTYPE, new PrototypeScopeHandler());
	}

	public void addScope(ScopeType type, ScopeHandler handler) {
		scopes.put(type, handler);
	}

	public void addClass(Class<?> cls) {
		classes.add(cls); // add to classes array
		Injectable i = cls.getAnnotation(Injectable.class); // add to injectable
		// classes array
		if (i != null) { // class is injectable
			String name = "";
			if (i.value().equals("")) {// no name set
				name = createName(cls);
			} else {
				name = i.value();
			}
			injectableClasses.put(name, cls);
		}
	}

	public void listClasses() {
		System.out.println("Listing registered classes:");
		for (Class<?> c : classes) {
			System.out.println(c.getName());
		}
	}

	public void listInjectableClasses() {
		System.out.println("Listing injectable classes:");
		for (Entry<String, Class<?>> entry : injectableClasses.entrySet()) {
			System.out.println("Name '" + entry.getKey() + "' Class: '"
					+ entry.getValue().getName() + "'");
		}
	}

	protected Object getInstance(String name)
			throws UnableToInstantiateException {
		Object instance = null;

		Class<?> cls = injectableClasses.get(name);
		if (cls == null) {
			throw new UnableToInstantiateException(name,
					new NoSuchInjectableException(name));
		}
		ScopeType st = null;
		try {
			st = cls.getAnnotation(Scope.class).value();
		} catch (Exception e) {
			st = ScopeType.PROTOTYPE; // default scope
		}
		if (scopes.get(st) == null) {
			throw new UnableToInstantiateException(st.name(),
					new NoSuchScopeException(st.name()));
		}
		instance = scopes.get(st).getInstance(name, cls);

		return instance;
	}

	protected void inject(Object instance) throws UnableToInstantiateException {
		Class<?> cls = instance.getClass();
		for (Field f : cls.getDeclaredFields()) {
			RequestInjection rio = f.getAnnotation(RequestInjection.class);
			if (rio != null) {
				String name = "";
				if (rio.value().equals("")) {
					name = f.getName();
				} else {
					name = rio.value();
				}
				f.setAccessible(true); // faster performance
				Object injection = get(name);
				try {
					f.set(instance, injection); // TODO: look for existing
					// setter
				} catch (Exception e) {
					throw new UnableToInstantiateException(name, e);
				}
			}
		}
	}

	public Object get(String name) throws UnableToInstantiateException {
		Object instance = null;
		try {
			instance = getInstance(name);
			this.inject(instance);
			if (instance == null) {
				throw new UnableToInstantiateException(name,
						new NullPointerException(
								"instance was null after generation"));
			}
		} catch (UnableToInstantiateException e) {
			throw e;
		} catch (Exception e) {
			throw new UnableToInstantiateException(name, e);
		}
		return instance;
	}

}
