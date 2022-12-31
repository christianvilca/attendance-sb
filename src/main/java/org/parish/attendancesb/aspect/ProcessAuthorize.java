package org.parish.attendancesb.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.parish.attendancesb.aspect.annotation.Function;
import org.parish.attendancesb.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

//@Component
@Aspect
@Configuration
public class ProcessAuthorize {

    @Autowired
    private UserService service;

//    @Pointcut("@annotation(org.parish.attendancesb.aspect.annotation.Authorize)")
//    public void authorizeAnnotation() {
//    }
//
//    @Around("authorizeAnnotation()")
//    public Object authorize(ProceedingJoinPoint jp) throws Throwable {
//        LogManager.getLogger(jp.getSignature().getDeclaringTypeName()).info("------------------------- o -------------------------");
//        String nameMethod = jp.getSignature().getName();
//        LogManager.getLogger(jp.getSignature().getDeclaringTypeName()).info(nameMethod);
//
//        //if (service.isAuthorize(nameMethod)) {
//        if (true) {
//            System.out.println("---- Fuera");
//            //jp.proceed();
//            try {
//                //if (true) {//"findAll".equals(nameMethod)){
//                    System.out.println("---- Dentro");
//                    return jp.proceed();
//            } catch (Throwable e) {
//                LogManager.getLogger("ERROR: ").info(e.getMessage());
//                Alert.error("No tiene autorizacion!");
//                throw new RuntimeException(e);
//            }
//        }
//        return jp.proceed();
//    }

    //@Around("within(@org.springframework.stereotype.Controller *) && @annotation(org.parish.attendancesb.aspect.annotation.Function)")
    public Object functionAccessCheck(final ProceedingJoinPoint pjp, Function function) throws Throwable {
        System.out.println("--------- Prueba");
        if (function != null) {
            String functionName = function.value();
            if (!canAccess(functionName)) {
                MethodSignature ms = (MethodSignature) pjp.getSignature();
                throw new RuntimeException("Can not Access " + ms.getMethod());
            }
        }

        Object o = pjp.proceed();
        return o;
    }

    protected boolean canAccess(String functionName) {
        if (functionName.length() == 0) {
            return true;
        } else {
            // Elimine todos los roles correspondientes al usuario actual y consulte la base de datos si el rol tiene permiso para acceder a functionName.
            return false;
        }
    }

    //@Around("@within(org.springframework.stereotype.Controller) ")
    public Object simpleAop(final ProceedingJoinPoint pjp) throws Throwable {
        try {
            Object[] args = pjp.getArgs();
            System.out.println("args:" + Arrays.asList(args));
            Object o = pjp.proceed();
            System.out.println("return :" + o);
            return o;

        } catch (Throwable e) {
            throw e;
        }
    }
}

