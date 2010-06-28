package ch.tobiasstaehli.di.tests;

import junit.framework.TestCase;

import org.junit.Test;

import ch.tobiasstaehli.di.SimpleIoCContainer;
import ch.tobiasstaehli.di.exceptions.UnableToInstantiateException;
import ch.tobiasstaehli.di.scopes.ScopeType;
import ch.tobiasstaehli.di.scopes.SingletonScopeHandler;

public class TestSingletonScopeHandler extends TestCase {

	@Test
	public final void testSingletonScope() throws UnableToInstantiateException {
		SimpleIoCContainer cont = new SimpleIoCContainer();
		cont.addScope(ScopeType.SINGLETON, new SingletonScopeHandler());
		cont.addClass(TestSingletonA.class); 

		TestSingletonA first = cont.get(TestSingletonA.class);
		TestSingletonA second = cont.get(TestSingletonA.class);
		assertEquals(first, second);
	}

}
