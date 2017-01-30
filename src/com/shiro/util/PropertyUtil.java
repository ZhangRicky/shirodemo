package com.shiro.util;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

/**
 * ��ȡpropertity�����ļ�������
 * @author Mark
 *
 */
public class PropertyUtil {
    private static Properties props;
    public static String getPropValue(String key) {
        DefaultResourceLoader loader=new DefaultResourceLoader();
        Resource resource=loader.getResource("classpath:config.properties");
        if(props==null){
            try {
                props = PropertiesLoaderUtils.loadProperties(resource);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return  props.getProperty(key);
    }
}
