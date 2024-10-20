package ru.t1.demo.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация для подсчета количества выполненных запросов к базе данных.
 * <p>
 * Каждый вызов метода, помеченного этой аннотацией, 
 * увеличивает счетчик общего количества выполненных запросов к базе данных.
 * </p>
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CountDbExecution {

}
