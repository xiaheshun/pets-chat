package cn.proxx.chat.pets.app.exception;

import android.content.Context;
import android.content.Intent;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;

/**
 * 全局异常处理器
 *
 * @author xiaheshun
 * @since 2022年4月29日 16:35
 */
public class CrashHandler implements UncaughtExceptionHandler {
    private UncaughtExceptionHandler mDefaultHandler;
    // CrashHandler实例
    private static CrashHandler INSTANCE = new CrashHandler();
    // 程序的Context对象
    private Context mContext;

    private CrashHandler() {
    }

    public static CrashHandler getInstance() {
        return INSTANCE;
    }

    /**
     * 初始化
     */
    public void init(Context context) {
        mContext = context;
        // 获取系统默认的UncaughtException处理
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        // 设置该CrashHandler为程序的默认处理
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 异常捕获
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (!handleException(ex) && mDefaultHandler != null) {
            // 如果用户没有处理则让系统默认的异常处理器来处
            mDefaultHandler.uncaughtException(thread, ex);
        } else {
            // 跳转到崩溃提示Activity
            Intent intent = new Intent(mContext, CrashDialog.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("msg", getCrashInfo(ex));
            mContext.startActivity(intent);
            System.exit(0);// 关闭已奔溃的app进程
        }
    }


    /**
     * 自定义错误捕获
     *
     * @param ex
     * @return true:如果处理了该异常信息;否则返回false.
     */
    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return false;
        }
        // 收集错误信息
        getCrashInfo(ex);
        return true;
    }

    /**
     * 收集错误信息
     *
     * @param ex
     */
    private String getCrashInfo(Throwable ex) {
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        return writer.toString();
    }

}
