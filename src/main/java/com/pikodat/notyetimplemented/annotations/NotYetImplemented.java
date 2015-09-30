package com.pikodat.notyetimplemented.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marker annotation for @NotYetImplemented
 *
 * @author veit@pikodat.com, 26.09.2015
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.METHOD)
public @interface NotYetImplemented {
}
