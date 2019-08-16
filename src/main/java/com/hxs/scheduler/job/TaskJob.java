package com.hxs.scheduler.job;

import com.alibaba.fastjson.JSONObject;
import com.hxs.scheduler.common.KeyConstant;
import com.hxs.scheduler.common.util.BeanHelper;
import com.hxs.scheduler.common.util.DateFormatHelper;
import com.hxs.scheduler.config.GlobalConfig;
import com.hxs.scheduler.service.ProcessService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;

import java.io.*;
import java.util.concurrent.TimeUnit;

@Slf4j
public class TaskJob implements Job {
    private static final String CMD_SUFFIX = "> %s";
    private static final String LOG_SUFFIX = ".log";
    private static String CMD_PREFIX;

    static {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.startsWith("win")) {
            CMD_PREFIX = "cmd /c ";
        } else {
            CMD_PREFIX = "bash -c ";
        }
    }

    private final GlobalConfig config = BeanHelper.getBean(GlobalConfig.class);
    private final ProcessService processService = BeanHelper.getBean(ProcessService.class);

    @Override
    public void execute(JobExecutionContext context) {
        JobDataMap jobDataMap = context.getMergedJobDataMap();
        String cmd = jobDataMap.getString(KeyConstant.CMD);
        int id = jobDataMap.getInt(KeyConstant.ID);
        cmd = wrapCmd(cmd, context.getTrigger(), id);
        log.info("JobExecute，jobDataMap={}", JSONObject.toJSONString(jobDataMap));
        processService.execCmd(cmd);
    }

    private String wrapCmd(String cmd, Trigger trigger, int taskId) {

        String logDir = config.getAbsLogDir();
        String logFileName = trigger.getJobKey() +
                "@" + taskId +
                "#" +
                DateFormatHelper.yMdHms(trigger.getStartTime()) +
                LOG_SUFFIX;
        logFileName = logFileName.replaceAll(" +", "_");
        String logFile = logDir + logFileName;
        String suffix = String.format(CMD_SUFFIX, logFile);
        cmd = CMD_PREFIX + cmd + suffix;
        return cmd;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        final Process process = Runtime.getRuntime().exec("cmd /c python hello.py", null, new File("G:/tmp"));
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
