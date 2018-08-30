package com.gwg.aspect.test;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class AspectE {

    @Around("execution(@com.gwg.aspect.test.Conditional * *.*(..)) && @annotation(conditional)" )
    public Object condition(ProceedingJoinPoint joinPoint, Conditional conditional) throws Throwable {

        System.out.println("entry point says hello!");
        return joinPoint.proceed();
    }
}