package ch.tobiasstaehli.di.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import ch.tobiasstaehli.di.scopes.ScopeType;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Scope {
	ScopeType value() default ScopeType.PROTOTYPE;
}
