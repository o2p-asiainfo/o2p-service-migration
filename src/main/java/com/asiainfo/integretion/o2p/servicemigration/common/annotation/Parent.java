package com.asiainfo.integretion.o2p.servicemigration.common.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 标识某一个对象的属性是当前对象的父对象
 * @author windy
 *
 */
@Retention(RUNTIME) @Target({FIELD, METHOD})
public @interface Parent {

}
