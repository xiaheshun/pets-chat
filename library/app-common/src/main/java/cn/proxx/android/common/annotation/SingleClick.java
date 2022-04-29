package cn.proxx.android.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 防止View被连续点击
 *
 * @author xiaheshun
 * @since 2022年4月29日 16:35
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SingleClick {

    long DEFAULT_INTERVAL_MILLIS = 1000;

    /**
     * @return 快速点击的间隔（ms），默认是1000ms
     */
    long value() default DEFAULT_INTERVAL_MILLIS;
}
