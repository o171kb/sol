package tt;

import org.apache.commons.lang.ClassUtils;
import org.apache.log4j.Logger;

import tt.com.utils.UtCoStringUtils;

/**
 * DESC : THINK-TREE LOGGER
 *
 * @Company think-tree.inc
 * @author ks-lee
 * @Date 2013. 1. 9. 오후 8:16:28
 */
@SuppressWarnings("rawtypes")
public final class TTLog {

    private TTLog() {
    }

    private static Logger logger = Logger.getLogger("ttlog");

    private static final String JOB_HANDLER_LOG_PREFIX = "=============";

    public static void debug(String msg, Class clazz, String id) {
        logger.debug(parseLogPrifix(clazz) + parseId(id) + msg);
    }

    public static void debug(String msg, Class clazz) {
        logger.debug(parseLogPrifix(clazz) + msg);
    }

    public static void info(String msg, Class clazz, String id) {
        logger.info(parseLogPrifix(clazz) + parseId(id) + msg);
    }

    public static void info(String msg, Class clazz) {
        logger.info(parseLogPrifix(clazz) + msg);
    }

    public static void warn(String msg, Class clazz, String id) {
        logger.warn(parseLogPrifix(clazz) + parseId(id) + msg);
    }

    public static void warn(String msg, Class clazz) {
        logger.warn(parseLogPrifix(clazz) + msg);
    }

    public static void error(String msg, Class clazz, String id) {
        logger.error(parseLogPrifix(clazz) + "::: ERROR ::: " + parseId(id) + msg);
    }

    public static void error(String msg, Class clazz) {
        if (msg.startsWith(JOB_HANDLER_LOG_PREFIX)) {
            logger.error(parseLogPrifix(clazz) + msg);
        } else {
            logger.error(parseLogPrifix(clazz) + "::: ERROR ::: " + msg);
        }
    }

    public static void fatal(String msg, Class clazz, String id) {
        logger.fatal(parseLogPrifix(clazz) + parseId(id) + msg);
    }

    public static void fatal(String msg, Class clazz) {
        logger.fatal(parseLogPrifix(clazz) + msg);
    }

    private static String parseLogPrifix(Class clazz) {

        String packageName = ClassUtils.getPackageName(clazz);

        if (packageName.startsWith("tt.com")) {
            return "[Common    ] ";
        } else if (packageName.startsWith("tt.component")) {
            return "[Component ] ";
            // } else if (packageName.startsWith("think.tree.front")) {
            // return "[Front     ] ";
        } else {
            return "[Unknown   ] ";
        }
    }

    private static String parseId(String id) {
        return UtCoStringUtils.isNotBlank(id) ? "[PROCESS ID: " + id + "] " : "";
    }
}
