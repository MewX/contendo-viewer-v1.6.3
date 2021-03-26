package com.sun.jna.platform.win32.COM.util.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Inherited
public @interface ComProperty {
  String name() default "";
  
  int dispId() default -1;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/sun/jna/platform/win32/COM/util/annotation/ComProperty.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */