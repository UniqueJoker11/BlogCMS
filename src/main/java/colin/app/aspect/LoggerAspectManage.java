package colin.app.aspect;

import colin.app.common.DateUtils;
import colin.app.core.pojo.Homework_LoggerEntity;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Created by joker on 15-2-3.
 */

@Aspect
@Component
public class LoggerAspectManage {
  /*  @Resource
    private LoggerManagerService loggerManagerService;*/

    //如果要设置多个切点可以使用 || 拼接
    @Before("execution(* colin.app.action.*.*(..))")
    public void saveAccessLogInfo(JoinPoint jp) {
        //获取日志对象
        Logger logger = Logger.getLogger(jp.getTarget().getClass());
        logger.info("当前访问的class是"+logger.getClass().getName());
        //存储当前的访问类的内容
        Homework_LoggerEntity loggerEntity = new Homework_LoggerEntity();
        loggerEntity.setCreateTime(DateUtils.getCurrentDate());
        loggerEntity.setAccessClass(jp.getTarget().getClass().getName());
        loggerEntity.setAccessUser("manager");
        loggerEntity.setLoggerContent("当前访问的类是" + jp.getTarget().getClass().getName() + "，当前的方法名是：" + jp.getSignature().getName());
        loggerEntity.setTag("before access");
      /*  loggerManagerService.saveLoggerInfo(loggerEntity);*/
    }
}
