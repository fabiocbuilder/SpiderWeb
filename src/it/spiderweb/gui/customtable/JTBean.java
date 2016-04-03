package it.spiderweb.gui.customtable;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *
 * @author agrimandi
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface JTBean {

    boolean active() default true;
}
