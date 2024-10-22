package ru.t1.demo.aspect;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import ru.t1.demo.repository.FakeDbRepository;

@Aspect
@Component
@Slf4j
public class DbAspect {

    private static Integer dbExecutions;

    static {
        dbExecutions = 0;
    }

    @Before("@annotation(LogDbExecution)")
    public void logDbExecution(JoinPoint joinPoint) {
        log.info("Accessing method: " + joinPoint.getSignature());
    }

    @AfterThrowing(pointcut = "@annotation(LogDbException)", throwing = "ex")
    public void logDbException(JoinPoint joinPoint, Exception ex) {
        log.error("Method: " + joinPoint.getSignature() + " has thrown: "
                + ex.getClass().getName() + "(" + ex.getMessage() + ")" + " with parameters: "
                + Arrays.toString(joinPoint.getArgs()));

    }

    @Around("@annotation(CatchDbExecution)")
    public void catchDbException(ProceedingJoinPoint proceedingJoinPoint) {

        try {
            proceedingJoinPoint.proceed();
        } catch (Throwable t) {
            log.warn("Suppressed. Method: " + proceedingJoinPoint.getSignature() + " has thrown: "
                    + t.getClass().getName() + "(" + t.getMessage() + ")" + " with parameters: "
                    + Arrays.toString(proceedingJoinPoint.getArgs()));

        }

    }

    @AfterReturning("@annotation(CountDbExecution)")
    public void logDbExecutionsQuantity(JoinPoint joinPoint) {

        Object target = joinPoint.getTarget();

        // Проверка типа, вдруг кто-то повесит аннотацию не в том классе
        if (target instanceof FakeDbRepository) {
            log.info("Successful DB executions: " + ++dbExecutions);

        }

    }

}
