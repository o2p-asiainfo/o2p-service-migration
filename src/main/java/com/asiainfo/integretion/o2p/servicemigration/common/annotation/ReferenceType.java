package com.asiainfo.integretion.o2p.servicemigration.common.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.asiainfo.integretion.o2p.servicemigration.domain.BasedBean;


@Retention(RUNTIME) @Target({FIELD, METHOD})
public @interface ReferenceType {
	
	Class<?> value() default BasedBean.class;
}
