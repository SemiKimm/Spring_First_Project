package com.nhnacademy.edu.springframework.project.aspect;

import java.util.logging.Logger;
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
    @Around("within(com.nhnacademy.edu.springframework.project.service..*)")
    public Object logSendMessage(ProceedingJoinPoint ptj) throws Throwable{
        StopWatch stopWatch = new StopWatch();
        try{
            stopWatch.start();
            return ptj.proceed();
        }finally {
            stopWatch.stop();
            System.out.println(ptj.getSignature()+":"+stopWatch.prettyPrint());
            log.info(ptj.getSignature()+":"+stopWatch.prettyPrint());
        }
    }
}
