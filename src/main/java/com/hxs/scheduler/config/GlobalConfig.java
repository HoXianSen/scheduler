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
    @Value("${spring.servlet.multipart.location}")
    private String scriptWorkspace;
    @Value("${scheduler.log-dir}")
    private String logDir;

    @Override
    public void afterPropertiesSet() throws Exception {
        File file = new File(scriptWorkspace);
        if (!file.isDirectory()) {
            if (!file.mkdirs()) {
                throw new Exception("创建scriptWorkspace目录失败：" + scriptWorkspace);
            }
        }
        this.processDir = file;

        /*logDir*/
        if (!logDir.endsWith("/")) {
            logDir += "/";
        }

        File logDirFile = new File(logDir);
        if (!logDirFile.exists()) {
            if (!logDirFile.mkdirs()) {
                throw new Exception("创建logDir目录失败：" + logDir);
            }
        }
    }
}
