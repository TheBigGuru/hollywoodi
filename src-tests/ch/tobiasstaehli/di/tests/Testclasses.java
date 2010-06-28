package ch.tobiasstaehli.di.tests;

import ch.tobiasstaehli.di.annotations.Injectable;
import ch.tobiasstaehli.di.annotations.RequestInjection;
import ch.tobiasstaehli.di.annotations.Scope;
import ch.tobiasstaehli.di.scopes.ScopeType;

class TestSimple {
	public TestSimple() {

	}
}

@Injectable
class TestA {
	public TestA() {
	}
}

@Injectable("secondTest")
class TestB {
	public TestB() {
	}
}

@Injectable
class TestC {

	@RequestInjection
	private TestA testA;

	public TestC() {
	}

	public boolean injectionExists() {
		return testA != null;
	}
}

@Injectable
class TestD {

	@RequestInjection
	private TestA testA;

	@RequestInjection("secondTest")
	private TestB testB;

	public TestD() {
	}

	public boolean injectionExists() {
		return testA != null && testB != null;
	}
}

@Injectable
class TestE {

	@RequestInjection
	private TestD testD;

	public TestE() {
	}

	public boolean injectionExists() {
		return testD != null && testD.injectionExists();
	}
}

interface IntA {
	public boolean test();
}

@Injectable
class ConcI implements IntA {

	public ConcI() {

	}

	public boolean test() {
		return true;
	}

}

@Injectable
class TestInt {

	@RequestInjection
	private IntA concI;

	public TestInt() {

	}

	public boolean test() {
		return concI != null && concI.test();
	}
}

@Injectable
@Scope(ScopeType.SINGLETON)
class TestSingletonA{
	public TestSingletonA(){
		
	}
}
