package tt.com.utils;

import java.io.IOException;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.stereotype.Component;

/**
 * DESC :
 *
 * @Company think-tree.inc
 * @author ks-lee
 * @Date 2013. 1. 3. 오전 11:17:33
 */
@Component
public class UtCoPropsUtils {

    private static PropsConfigurer propsConfigurer;

    @SuppressWarnings("static-access")
    @Resource(name = "propsConfigurer")
    public void setPropsConfigurer(PropsConfigurer propsConfigurer) {
        this.propsConfigurer = propsConfigurer;
    }

    public static String getAsString(String key) {
        return String.valueOf(propsConfigurer.getMergedProperties().getProperty(key));
    }

    public static boolean getAsBoolean(String key) {
        return Boolean.valueOf(propsConfigurer.getMergedProperties().getProperty(key));
    }

    public static int getAsInt(String key) {
        return Integer.valueOf(propsConfigurer.getMergedProperties().getProperty(key));
    }

    public static float getAsFloat(String key) {
        return Float.valueOf(propsConfigurer.getMergedProperties().getProperty(key));
    }

    public static long getAsLong(String key) {
        return Long.valueOf(propsConfigurer.getMergedProperties().getProperty(key));
    }

    public static double getAsDouble(String key) {
        return Double.valueOf(propsConfigurer.getMergedProperties().getProperty(key));
    }

    /**
     * DESC :
     *
     * @Company think-tree.inc
     * @author ks-lee
     * @Date 2013. 1. 3. 오전 11:17:50
     */
    public static class PropsConfigurer extends PropertyPlaceholderConfigurer {

        private Properties properties;

        public Properties getMergedProperties() {

            if (properties == null) {
                try {
                    properties = mergeProperties();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return properties;
        }
    }

}