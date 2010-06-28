package ch.tobiasstaehli.di.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for ch.tobiasstaehli.di.tests");

		suite.addTestSuite(TestSimpleIoCContainer.class);
		suite.addTestSuite(TestPrototypeScopeHandler.class);
		suite.addTestSuite(TestSingletonScopeHandler.class);

		return suite;
	}

}
