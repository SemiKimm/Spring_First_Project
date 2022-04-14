package com.nhnacademy.edu.springframework.project.aspect;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class LoggingAspect {
    private static final Log log = LogFactory.getLog(LoggingAspect.class);

    @Around("execution(public * *(..))")
    public Object logSendMessage(ProceedingJoinPoint ptj) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        try {
            stopWatch.start();
            return ptj.proceed();
        } finally {
            stopWatch.stop();
            log.info(ptj.getSignature() + ":" + stopWatch.prettyPrint());
        }
    }
}
