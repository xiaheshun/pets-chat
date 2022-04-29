package cn.proxx.android.common.annotation;

import android.util.Log;
import android.view.View;

import com.xuexiang.xutil.common.ClickUtils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author xiaheshun
 * @since 2022年4月29日 16:35
 */
@Aspect
public class SingleClickAspect {

    @Pointcut("within(@hy.android.yt1.common.annotation.SingleClick *)")
    public void withinAnnotatedClass() {
    }

    @Pointcut("execution(!synthetic * *(..)) && withinAnnotatedClass()")
    public void methodInsideAnnotatedType() {
    }

    @Pointcut("execution(@hy.android.yt1.common.annotation.SingleClick * *(..)) || methodInsideAnnotatedType()")
    public void method() {
    }

    @Around("method() && @annotation(singleClick)")
    public void aroundJoinPoint(ProceedingJoinPoint joinPoint, SingleClick singleClick) throws Throwable {
        View view = null;
        for (Object arg : joinPoint.getArgs()) {
            if (arg instanceof View) {
                view = (View) arg;
                break;
            }
        }
        if (view != null) {
            if (!ClickUtils.isFastDoubleClick(view, singleClick.value())) {
                joinPoint.proceed();//不是快速点击，执行原方法
            } else {
                Log.e("点击事件拦截器", ":发生快速点击，View id:" + view.getId());
            }
        }
    }

}
