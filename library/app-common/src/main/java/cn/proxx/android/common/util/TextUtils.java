package cn.proxx.android.common.util;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import androidx.annotation.Nullable;

/**
 * @author XiaHeShun
 * @since 2020年7月7日09:59:16
 */
public class TextUtils {

    /**
     * 判断是否为空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(@Nullable Object str) {
        if (null == str) {
            return true;
        }
        return ("".equals(str) || "null".equals(str) || " ".equals(str) || "{}".equals(str) || "[]".equals(str) || "\"\"".equals(str));
    }

    /**
     * 判断是否为空
     *
     * @param str
     * @return
     */
    public static boolean isNotEmpty(@Nullable Object str) {
        if (null == str) {
            return false;
        }
        return !("".equals(str) || "null".equals(str) || " ".equals(str) || "{}".equals(str) || "[]".equals(str) || "\"\"".equals(str));
    }

    /**
     * 其中包含空字符串
     * || 关系
     *
     * @param str
     * @return
     */
    public static boolean hasEmpty(String... str) {
        boolean flag = false;
        for (String s : str) {
            if (null == s) {
                flag = true;
                break;
            } else {
                if (isEmpty(s)) {
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }

    /**
     * 其中是否有不为空的字符串，可以包含空字符串
     * || 关系
     *
     * @param str
     * @return
     */
    public static boolean hasNotEmpty(String... str) {
        for (String s : str) {
            if (isNotEmpty(s)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 其中均是空的字符串,不允许包含不为空的
     * && 关系
     *
     * @param str
     * @return
     */
    public static boolean isAllEmpty(String... str) {
        for (String s : str) {
            if (isNotEmpty(s)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 其中均不包含空的字符串,不允许包含空
     * && 关系
     *
     * @param str
     * @return
     */
    public static boolean isAllNotEmpty(String... str) {
        if (hasEmpty(str)) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 比较是否相等
     *
     * @param s1
     * @param s2
     * @return
     */
    public static boolean equals(String s1, String s2) {
        if (isEmpty(s1) || isEmpty(s2)) {
            return false;
        } else {
            if (s1.equals(s2)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 比较是否相等
     *
     * @param s1
     * @param s2
     * @return
     */
    public static boolean notEquals(String s1, String s2) {
        if (isEmpty(s1) || isEmpty(s2)) {
            return true;
        } else {
            if (s1.equals(s2)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 强制转换为UTF-8编码
     *
     * @param str
     * @return
     */
    public static String utf8(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 判空
     *
     * @param str
     * @return
     */
    public static String checkEmpty(String str) {
        if (isEmpty(str)) {
            return "";
        } else {
            return str;
        }
    }

    /**
     * 截取小数点后几位
     *
     * @param num 数字
     * @param n   保留小数点后几位
     * @return
     */
    public static String subStr(Double num, int n) {
        String numStr = Double.toString(num);
        int i = numStr.indexOf(".");
        if (n == 0) {
            return numStr.substring(0, i);
        }
        if (i == -1) {
            return numStr;
        }
        numStr = numStr.substring(0, (i + n + 1) > numStr.length() ? numStr.length() : i + n + 1);
        return numStr;
    }

    public static String subStr(Float num, int n) {
        String numStr = Float.toString(num);
        int i = numStr.indexOf(".");
        if (n == 0) {
            return numStr.substring(0, i);
        }
        if (i == -1) {
            return numStr;
        }
        numStr = numStr.substring(0, (i + n + 1) > numStr.length() ? numStr.length() : i + n + 1);
        return numStr;
    }

    public static String subStr(Integer num, int n) {
        String numStr = Integer.toString(num);
        int i = numStr.indexOf(".");
        if (n == 0) {
            return numStr.substring(0, i);
        }
        if (i == -1) {
            return numStr;
        }
        numStr = numStr.substring(0, (i + n + 1) > numStr.length() ? numStr.length() : i + n + 1);
        return numStr;
    }

    public static String decodeUnicode(String dataStr) {
        int start = 0;
        int end = 0;
        final StringBuffer buffer = new StringBuffer();
        while (start > -1) {
            end = dataStr.indexOf("\\u", start + 2);
            String charStr = "";
            if (end == -1) {
                charStr = dataStr.substring(start + 2, dataStr.length());
            } else {
                charStr = dataStr.substring(start + 2, end);
            }
            char letter = 0;
            try {
                // 16进制parse整形字符串
                letter = (char) Integer.parseInt(charStr, 16);
                buffer.append(new Character(letter).toString());
            } catch (Exception e) {
                System.out.println("最后字符集转换错误，无碍！");
            }

            start = end;
        }
        return buffer.toString();
    }

}
