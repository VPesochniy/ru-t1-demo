package ru.t1.demo.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация для перехвата и обработки исключений базы данных.
 * <p>
 * Исключения, возникающие в методах, аннотированных этой аннотацией, 
 * будут перехвачены и обработаны. 
 * Обработка может включать в себя логирование, подавление исключения 
 * или выполнение другой логики.
 * Эта аннотация не должна использоваться вместе с {@link LogDbException}, 
 * так как они предоставляют конфликтующие механизмы обработки исключений.
 * </p>
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CatchDbExecution {

}
