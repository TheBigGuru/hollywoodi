package ch.tobiasstaehli.di.tests;

import junit.framework.TestCase;

import org.junit.Test;

import ch.tobiasstaehli.di.SimpleIoCContainer;
import ch.tobiasstaehli.di.exceptions.UnableToInstantiateException;
import ch.tobiasstaehli.di.scopes.PrototypeScopeHandler;
import ch.tobiasstaehli.di.scopes.ScopeType;

public class TestPrototypeScopeHandler extends TestCase {

	public final void testSingletonScope() throws UnableToInstantiateException {
		SimpleIoCContainer cont = new SimpleIoCContainer();
		cont.addScope(ScopeType.PROTOTYPE, new PrototypeScopeHandler());
		cont.addClass(TestA.class);

		TestA first = cont.get(TestA.class);
		TestA second = cont.get(TestA.class);
		assertTrue(first != second);
	}
}
