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
public class GlobalConfig implements InitializingBean {
    private File processDir;
    private String charset = "utf-8";
    @Value("${spring.servlet.multipart.location}")
    private String scriptLocation;
    @Value("${scheduler.log-location}")
    private String logLocation;

    @Override
    public void afterPropertiesSet() throws Exception {
        File file = new File(scriptLocation);
        if (!file.isDirectory()) {
            if (!file.mkdirs()) {
                throw new Exception("创建scriptWorkspace目录失败：" + scriptLocation);
            }
        }
        this.processDir = file;

        /*logLocation*/
        if (!logLocation.endsWith("/")) {
            logLocation += "/";
        }

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
        }
    }
}
