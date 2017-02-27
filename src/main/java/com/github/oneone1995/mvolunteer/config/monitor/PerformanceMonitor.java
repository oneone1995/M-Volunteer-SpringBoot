package com.github.oneone1995.mvolunteer.config.monitor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * Created by wangl on 2017/2/27.
 */
@Aspect
@Component
public class PerformanceMonitor {
    protected Logger log = LoggerFactory.getLogger(PerformanceMonitor.class);
    @Pointcut("execution(public * com.github.oneone1995.mvolunteer.web..*(..))")
    private void controllerLayer() {
    }

    /**
     * Monitor the elapsed time of method on controller layer, in
     * order to detect performance problems as soon as possible.
     * If elapsed time > 1 s, log it as an error. Otherwise, log it
     * as an info.
     */
    @Around("controllerLayer()")
    public Object monitorElapsedTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // Timing the method in controller layer
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object result = proceedingJoinPoint.proceed();
        stopWatch.stop();

        // Log the elapsed time
        double elapsedTime = stopWatch.getTotalTimeSeconds();
        Signature signature = proceedingJoinPoint.getSignature();
        String infoString = "[" + signature.toShortString() + "][Elapsed time: " + elapsedTime + " s]";
        if (elapsedTime > 1) {
            log.error(infoString + "[Note that it's time consuming!]");
        } else {
            log.info(infoString);
        }

        // Return the result
        return result;
    }
}
