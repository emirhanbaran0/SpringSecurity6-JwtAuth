package com.emirhanbaran.springsecurity6jwt.aop;

import com.emirhanbaran.springsecurity6jwt.annotation.IdGuard;
import com.emirhanbaran.springsecurity6jwt.config.EncryptionManager;
import com.emirhanbaran.springsecurity6jwt.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
@RequiredArgsConstructor
public class IdGuardAspect {


    private final JwtService jwtService;
    private final EncryptionManager encryptionManager;

    @Before("@annotation(idGuard)")
    public void execute(JoinPoint joinPoint, IdGuard idGuard) {

        int argIndex = idGuard.parameterIndex();
        if(argIndex < 0){
            argIndex = 0;
        }

        Object[] args = joinPoint.getArgs();
        if(args == null || args.length == 0) {
            throw new RuntimeException("ACCESS ERROR FOR INVALID RESOURCE");
        }

        Object idParameterObj = args[argIndex];
        if(idParameterObj == null) {
            throw new RuntimeException("ACCESS ERROR FOR INVALID RESOURCE");
        }

        String expectedTicket = encryptionManager.encrypt(idParameterObj.toString());

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String token = jwtService.extractJwt(request);
        if(!StringUtils.hasText(token)) {
            throw new RuntimeException("ACCESS ERROR FOR INVALID RESOURCE");
        }

        String realTicket = jwtService.extractTicket(token);
        if(!(StringUtils.hasText(expectedTicket) && expectedTicket.contains(realTicket)) ) {
            throw new RuntimeException("ACCESS ERROR FOR INVALID RESOURCE");
        }
    }
}
