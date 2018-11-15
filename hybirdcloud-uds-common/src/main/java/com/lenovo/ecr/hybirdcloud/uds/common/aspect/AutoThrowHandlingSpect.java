package com.lenovo.ecr.hybirdcloud.uds.common.aspect;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
public class AutoThrowHandlingSpect {
    static final Logger LOG = LoggerFactory.getLogger(AutoThrowHandlingSpect.class);

    @Pointcut("(@annotation(org.springframework.web.bind.annotation.GetMapping) || @annotation(org.springframework.web.bind.annotation.PostMapping) || @annotation(org.springframework.web.bind.annotation.RequestMapping)) && !@annotation(com.lenovo.ecr.hybirdcloud.uds.common.annotation.DisableAutoThrowHandling)")
    public void controllerHanling(){

    }

    @AfterThrowing(pointcut = "controllerHanling()", throwing = "e")
    public void logThrowing(Exception e){
        LOG.error("error:",e);
    }
}
