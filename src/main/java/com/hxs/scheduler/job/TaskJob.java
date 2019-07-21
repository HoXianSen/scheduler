package com.hxs.scheduler.job;

import com.hxs.scheduler.common.KeyConstant;
import com.hxs.scheduler.common.util.BeanHelper;
import com.hxs.scheduler.config.GlobalConfig;
import com.hxs.scheduler.service.ProcessService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;

import java.io.*;
import java.util.concurrent.TimeUnit;

@Slf4j
public class TaskJob implements Job {
    private static final String CMD_PREFIX = "nohup ";
    private static final String CMD_SUFFIX = "> %s &";

    private final GlobalConfig config = BeanHelper.getBean(GlobalConfig.class);
    private final ProcessService processService = BeanHelper.getBean(ProcessService.class);

    @Override
    public void execute(JobExecutionContext context) {
        JobDataMap jobDataMap = context.getMergedJobDataMap();
        String cmd = jobDataMap.getString(KeyConstant.CMD);
        cmd = changeCmd(cmd, context.getJobDetail());
        try {
            processService.execCmd(cmd);
        } catch (IOException e) {
            log.error("TaskJob中运行cmd出现异常", e);
        }
    }

    private String changeCmd(String cmd, JobDetail jobDetail) {
        String logDir = config.getLogDir();
        String logFileName = jobDetail.getKey().toString() + ".log";
        String logFile = logDir + logFileName;
        String suffix = String.format(CMD_SUFFIX, logFile);
        cmd = CMD_PREFIX + cmd + suffix;
        return cmd;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        final Process process = Runtime.getRuntime().exec("ping localhost", null, new File("D:/tmp"));
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
