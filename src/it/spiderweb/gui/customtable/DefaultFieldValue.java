package it.spiderweb.gui.customtable;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author agrimandi
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DefaultFieldValue
{
	String defaultStringValue() default "";

	int defaultIntValue() default 0;

	boolean defaultBoolValue() default false;
}
