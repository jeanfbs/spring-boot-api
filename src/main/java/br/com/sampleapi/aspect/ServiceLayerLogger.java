package br.com.sampleapi.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
@Aspect
@Slf4j
public final class ServiceLayerLogger {

    private static final String PACKAGE = "br.com.sampleapi..*";
    @Around("within(" + PACKAGE + ") && @within(org.springframework.stereotype.Service)")
    public Object logExecutionTimeMono(ProceedingJoinPoint joinPoint) throws Throwable {
        Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        logBeforeStart(logger, joinPoint);
        Object proceed = joinPoint.proceed();
        logAfterMethod(joinPoint,  logger);
        return proceed;
    }


    private void logBeforeStart(Logger logger, ProceedingJoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String[] parameterNames = methodSignature.getParameterNames();
        final var arguments = joinPoint.getArgs();
        String params = IntStream.range(0, arguments.length)
                .mapToObj(i -> String.format("%s=%s", parameterNames[i], arguments[i]))
                .collect(Collectors.joining(", "));

        if (!params.isEmpty()) {
            logger.debug("Started executing [{}] with arguments [{}]", methodName, params);
        } else {
            logger.debug("Started executing [{}]", methodName);
        }
    }

    private void logAfterMethod(JoinPoint joinPoint, Logger logger) {
        logger.debug("Finished executing [{}]",
                joinPoint.getSignature().getName());
    }
}
