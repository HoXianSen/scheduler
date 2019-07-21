package com.hxs.scheduler.service;

import com.hxs.scheduler.config.GlobalConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class ProcessService {
    @Resource
    private GlobalConfig config;

    public void execCmd(String cmd) throws IOException {
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec(cmd, null, config.getProcessDir());
        log.info("执行cmd：{}, 最大等待时间", cmd);
        try {
            process.waitFor(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            log.error(String.format("process等待期间被中断，cmd=%s", cmd), e);
        }
        if (process.isAlive()) {
            log.error("强制停止process，cmd={}", cmd);
            process.destroy();
        }
    }
}
