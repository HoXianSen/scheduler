package com.hxs.scheduler.job;

import com.hxs.scheduler.common.KeyConstant;
import com.hxs.scheduler.common.util.BeanHelper;
import com.hxs.scheduler.common.util.DateFormatHelper;
import com.hxs.scheduler.config.GlobalConfig;
import com.hxs.scheduler.service.ProcessService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Slf4j
public class TaskJob implements Job {
    private static final String LOG_SUFFIX = ".log";
    private static String CMD_PREFIX = "";

    static {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.startsWith("win")) {
            CMD_PREFIX = "cmd /c ";
        }
    }

    private final GlobalConfig config = BeanHelper.getBean(GlobalConfig.class);
    private final ProcessService processService = BeanHelper.getBean(ProcessService.class);

    @Override
    public void execute(JobExecutionContext context) {
        JobDataMap jobDataMap = context.getMergedJobDataMap();
        String cmd = jobDataMap.getString(KeyConstant.CMD);
        String logFilePath = getLogFilePath(context.getJobDetail());
        File logFile = new File(logFilePath);
        if (!logFile.exists()) {
            try {
                if (!logFile.createNewFile()) {
                    log.error("创建日志文件失败，logFilePath={}", logFilePath);
                    return;
                }
            } catch (IOException e) {
                log.error(String.format("创建日志文件异常，logFilePath=%s", logFilePath), e);
            }
        }
        cmd = CMD_PREFIX + cmd;
        processService.execCmd(cmd, logFile);
    }

    private String getLogFilePath(JobDetail jobDetail) {
        String logDir = config.getLogLocation();
        String logFileName = jobDetail.getKey() + LOG_SUFFIX;
        logFileName = logFileName.replaceAll(" +", "_");
        String logFilePath = logDir + logFileName;
        return logFilePath;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        final Process process = Runtime.getRuntime().exec("cmd /c python hello.py", null, new File("G:/tmp/script"));
        new Thread(() -> {
            try {
                InputStream in = process.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in, "GBK"));
                reader.lines().forEach(System.out::println);
                System.out.println("in close");
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        process.waitFor(5, TimeUnit.SECONDS);
        if (process.isAlive()) {
            System.out.println("timeout");
            process.destroy();
        }
    }
}
