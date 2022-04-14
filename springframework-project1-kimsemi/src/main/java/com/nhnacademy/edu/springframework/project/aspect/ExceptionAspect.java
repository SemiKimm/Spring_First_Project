package com.nhnacademy.edu.springframework.project.aspect;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExceptionAspect {
    Log log = LogFactory.getLog(ExceptionAspect.class);
    @AfterThrowing(
        pointcut = "within(com.nhnacademy.edu.springframework.project.config.SpringConfig)",
        throwing = "e"
    )
    public void doIllegalStateException(IllegalStateException e){
        log.error(e.getMessage());
    }
}
