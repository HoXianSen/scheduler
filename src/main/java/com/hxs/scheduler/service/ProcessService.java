package com.hxs.scheduler.service;

import com.google.common.io.CharSink;
import com.google.common.io.FileWriteMode;
import com.google.common.io.Files;
import com.hxs.scheduler.common.util.DateFormatHelper;
import com.hxs.scheduler.config.GlobalConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.nio.charset.Charset;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class ProcessService {
    private static final int MAX_WAIT_MINUTES = 1;
    private final ThreadPoolExecutor processLogExecutor = new ThreadPoolExecutor(5, 10,
            1, TimeUnit.MINUTES, new ArrayBlockingQueue<>(50));
    @Resource
    private GlobalConfig config;

    public void execCmd(String cmd, File logFile) {
        Runtime runtime = Runtime.getRuntime();
        Process process = null;
        try {
            process = runtime.exec(cmd, null, config.getProcessDir());
            logProcessOut(process, logFile);
            log.info("执行cmd：{}，脚本目录={}，日志文件={}，最大等待时间：{}分钟",
                    cmd, config.getProcessDir().getAbsolutePath(), logFile.getAbsolutePath(), MAX_WAIT_MINUTES);
            process.waitFor(MAX_WAIT_MINUTES, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            log.error(String.format("process等待期间被中断，cmd=%s", cmd), e);
        } catch (IOException e) {
            log.error(String.format("Process执行cmd出现异常，cmd=%s", cmd), e);
        } finally {
            if (process != null && process.isAlive()) {
                log.error("超过最大等待时间，强制停止process，cmd={}", cmd);
                process.destroy();
            }
        }
    }

    private void logProcessOut(Process process, File logFile) throws IOException {
        CharSink sink = Files.asCharSink(logFile, Charset.forName(config.getCharset()), FileWriteMode.APPEND);
        sink.write(System.lineSeparator());
        sink.write(System.lineSeparator());
        sink.write("[");
        sink.write(DateFormatHelper.now_yMdHms());
        sink.write("]");
        sink.write(System.lineSeparator());

        logProcessInfo(process, sink);
        logProcessErr(process, sink);
    }

    private void logProcessInfo(Process process, CharSink sink) {
        processLogExecutor.execute(() -> {
            InputStream is = process.getInputStream();
            try {
                record(is, sink);
            } catch (IOException e) {
                log.error("process记录文件时异常(INFO)", e);
            }
        });
    }

    private void logProcessErr(Process process, CharSink sink) {
        processLogExecutor.execute(() -> {
            try {
                InputStream is = process.getErrorStream();
                record(is, sink);
            } catch (IOException e) {
                log.error("process记录文件时异常(ERR)", e);
            }
        });
    }

    private void record(InputStream is, CharSink sink) throws IOException {
        InputStreamReader isr = new InputStreamReader(is, config.getCharset());
        BufferedReader reader = new BufferedReader(isr);
        sink.writeLines(reader.lines());
        reader.close();
        isr.close();
    }
}
