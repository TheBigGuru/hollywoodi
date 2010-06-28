package ch.tobiasstaehli.di.tests;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import ch.tobiasstaehli.di.SimpleIoCContainer;
import ch.tobiasstaehli.di.exceptions.NoSuchInjectableException;
import ch.tobiasstaehli.di.exceptions.NoSuchScopeException;
import ch.tobiasstaehli.di.exceptions.UnableToInstantiateException;
import ch.tobiasstaehli.di.scopes.ScopeType;
import ch.tobiasstaehli.di.scopes.SingletonScopeHandler;

public class TestSimpleIoCContainer extends TestCase {

	private SimpleIoCContainer cont;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		cont = new SimpleIoCContainer();
	}

	/**
	 * Test method for
	 * {@link ch.tobiasstaehli.di.SimpleIoCContainer#addClass(java.lang.Class)}.
	 */
	@Test
	public final void testAddClass() {
		cont.addClass(TestA.class);
		assertTrue(true);
	}

	/**
	 * Test method for
	 * {@link ch.tobiasstaehli.di.SimpleIoCContainer#addScope(ch.tobiasstaehli.di.scopes.ScopeType, ch.tobiasstaehli.di.ScopeHandler)}
	 * .
	 */
	@Test
	public final void testAddScope() {
		cont.addScope(ScopeType.SINGLETON, new SingletonScopeHandler());
		assertTrue(true);
	}

	/**
	 * Test method for
	 * {@link ch.tobiasstaehli.di.SimpleIoCContainer#get(java.lang.String)}.
	 * 
	 * @throws IllegalAccessException
	 * @throws NoSuchScopeException
	 * @throws NoSuchInjectableException
	 * @throws UnableToInstantiateException
	 */
	@Test
	public final void testSimpleInjection() throws UnableToInstantiateException {
		cont.addClass(TestA.class);
		assertTrue(cont.get(TestA.class) != null);
	}

	/**
	 * Test method for
	 * {@link ch.tobiasstaehli.di.SimpleIoCContainer#get(java.lang.String)}.
	 * 
	 * @throws IllegalAccessException
	 * @throws NoSuchScopeException
	 * @throws NoSuchInjectableException
	 * @throws UnableToInstantiateException
	 */
	@Test
	public final void testNamedInjection() throws NoSuchInjectableException,
			NoSuchScopeException, IllegalAccessException,
			UnableToInstantiateException {
		cont.addClass(TestB.class);
		assertTrue(cont.get("secondTest") != null);
	}

	/**
	 * Test method for
	 * {@link ch.tobiasstaehli.di.SimpleIoCContainer#get(java.lang.String)}.
	 * 
	 * @throws IllegalAccessException
	 * @throws NoSuchScopeException
	 * @throws NoSuchInjectableException
	 * @throws UnableToInstantiateException
	 */
	@Test
	public final void testKompexInjection() throws NoSuchInjectableException,
			NoSuchScopeException, IllegalAccessException,
			UnableToInstantiateException {
		cont.addClass(TestC.class);
		cont.addClass(TestA.class);
		assertTrue(cont.get(TestC.class).injectionExists());
	}

	/**
	 * Test method for
	 * {@link ch.tobiasstaehli.di.SimpleIoCContainer#get(java.lang.String)}.
	 * 
	 * @throws IllegalAccessException
	 * @throws NoSuchScopeException
	 * @throws NoSuchInjectableException
	 * @throws UnableToInstantiateException
	 */
	@Test
	public final void testKompexInjectionWithTwoInjections()
			throws NoSuchInjectableException, NoSuchScopeException,
			IllegalAccessException, UnableToInstantiateException {
		cont.addClass(TestB.class);
		cont.addClass(TestA.class);
		cont.addClass(TestD.class);
		assertTrue(cont.get(TestD.class).injectionExists());
	}

	/**
	 * Test method for
	 * {@link ch.tobiasstaehli.di.SimpleIoCContainer#get(java.lang.String)}.
	 * 
	 * @throws IllegalAccessException
	 * @throws NoSuchScopeException
	 * @throws NoSuchInjectableException
	 * @throws UnableToInstantiateException
	 */
	@Test
	public final void testKompexInjectionWithNestedInjection()
			throws NoSuchInjectableException, NoSuchScopeException,
			IllegalAccessException, UnableToInstantiateException {
		cont.addClass(TestA.class);
		cont.addClass(TestB.class);
		cont.addClass(TestC.class);
		cont.addClass(TestD.class);
		cont.addClass(TestE.class);
		assertTrue(cont.get(TestE.class).injectionExists());
	}

	/**
	 * Test method for
	 * {@link ch.tobiasstaehli.di.SimpleIoCContainer#get(java.lang.String)}.
	 * 
	 * @throws UnableToInstantiateException
	 * 
	 * @throws IllegalAccessException
	 * @throws NoSuchScopeException
	 * @throws NoSuchInjectableException
	 */
	public final void testNonregisteredInjection()
			throws UnableToInstantiateException {
		cont.addClass(TestC.class);
		try {
			cont.get(TestC.class);
		} catch (UnableToInstantiateException e) {
			assertTrue(true);
		}
	}

	/**
	 * Test method for
	 * {@link ch.tobiasstaehli.di.SimpleIoCContainer#get(java.lang.String)}.
	 * 
	 * @throws UnableToInstantiateException
	 * 
	 * @throws IllegalAccessException
	 * @throws NoSuchScopeException
	 * @throws NoSuchInjectableException
	 */
	public final void testNonAnnotated() throws UnableToInstantiateException {
		cont.addClass(TestSimple.class);
		try {
			cont.get(TestSimple.class);
		} catch (UnableToInstantiateException e) {
			assertTrue(true);
		}

	}

	/**
	 * Test method for
	 * {@link ch.tobiasstaehli.di.SimpleIoCContainer#get(java.lang.String)}.
	 * 
	 * @throws UnableToInstantiateException
	 * 
	 * @throws IllegalAccessException
	 * @throws NoSuchScopeException
	 * @throws NoSuchInjectableException
	 */
	@Test
	public final void testInterface() throws UnableToInstantiateException {
		cont.addClass(TestInt.class);
		cont.addClass(ConcI.class);

		assertTrue(cont.get(TestInt.class).test());
	}
}
