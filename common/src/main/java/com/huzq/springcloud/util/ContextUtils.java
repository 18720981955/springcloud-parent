package com.huzq.springcloud.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;

/**
 * spring容器上下文对象
 *
 * @author JackHu
 * @email 790327374@qq.com
 * @date 2019/3/28 15:54
 */
public class ContextUtils {
    private static ApplicationContext applicationContext;

    private static Logger logger = LoggerFactory.getLogger(ContextUtils.class);

    public static void setApplicationContext(ApplicationContext applicationContext) {
        synchronized (ContextUtils.class) {
            logger.debug("setApplicationContext, notifyAll");
            ContextUtils.applicationContext = applicationContext;
            ContextUtils.class.notifyAll();
        }
    }

    public static ApplicationContext getApplicationContext() {
        synchronized (ContextUtils.class) {
            while (applicationContext == null) {
                try {
                    logger.debug("getApplicationContext, wait...");
                    ContextUtils.class.wait(60000);
                    if (applicationContext == null) {
                        logger.warn("Have been waiting for ApplicationContext to be set for 1 minute", new Exception());
                    }
                } catch (InterruptedException ex) {
                    logger.debug("getApplicationContext, wait interrupted");
                }
            }
            return applicationContext;
        }
    }

    /**
     * 获取 bean
     *
     * @param name bean 名称
     * @return bean
     */
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    /**
     * 获取 bean
     *
     * @param cls bean 的类型
     * @return bean
     */
    public static <T> T getBean(Class<T> cls) {
        return getApplicationContext().getBean(cls);
    }

    /**
     * 触发事件
     *
     * @param event 事件
     */
    public static void publishEvent(ApplicationEvent event) {
        getApplicationContext().publishEvent(event);
    }

}

