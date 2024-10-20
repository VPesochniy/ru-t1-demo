package ru.t1.demo.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация для логирования вызова запросов к базе данных.
 * <p>
 * Методы, помеченные этой аннотацией, будут иметь логирование 
 * факта вызова запроса к базе данных.
 * </p>
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogDbExecution {
    
}
