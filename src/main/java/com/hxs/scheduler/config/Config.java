package com.hxs.scheduler.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Data
@Configuration
@Slf4j
public class Config implements InitializingBean {
    @Value("${scheduler.script-location}")
    private String scriptLocation;

    /**
     * 脚本运行目录
     */
    private File processDir;
    private String charset;
    private String logLocation;

    @Override
    public void afterPropertiesSet() throws Exception {
        /*scriptLocation*/
        if (!scriptLocation.endsWith("/")) {
            scriptLocation += "/";
        }
        File file = new File(scriptLocation);
        if (!file.isDirectory()) {
            if (!file.mkdirs()) {
                throw new Exception("创建scriptWorkspace目录失败：" + scriptLocation);
            }
        }
        this.processDir = file;


        logLocation = scriptLocation + "log/";

        File logDirFile = new File(logLocation);
        if (!logDirFile.exists()) {
            if (!logDirFile.mkdirs()) {
                throw new Exception("创建logDir目录失败：" + logLocation);
            }
        }

        /*charset*/
        String os = System.getProperty("os.name").toLowerCase();
        if (os.startsWith("win")) {
            charset = "GBK";
        } else {
            charset = "utf-8";
        }
    }
}
