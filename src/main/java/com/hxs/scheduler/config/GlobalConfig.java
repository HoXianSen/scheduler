package com.hxs.scheduler.config;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Data
@Configuration
public class GlobalConfig implements InitializingBean {
    private File processFolder;
    @Value("${spring.servlet.multipart.location}")
    private String workspace;

    @Override
    public void afterPropertiesSet() throws Exception {
        this.processFolder = new File(workspace);
    }
}
