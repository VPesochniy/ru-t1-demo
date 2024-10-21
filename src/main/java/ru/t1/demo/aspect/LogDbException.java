package ru.t1.demo.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация для логирования исключений базы данных.
 * <p>
 * При возникновении исключения в методе, аннотированном этой аннотацией, 
 * информация об исключении будет записана в лог.
 * Эта аннотация не должна использоваться вместе с {@link CatchDbExecution}, 
 * так как они предоставляют конфликтующие механизмы обработки исключений.
 * </p>
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogDbException {

}
