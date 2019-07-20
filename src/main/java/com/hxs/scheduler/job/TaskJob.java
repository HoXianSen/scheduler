package com.hxs.scheduler.job;

import com.hxs.scheduler.common.KeyConstant;
import com.hxs.scheduler.common.util.BeanHelper;
import com.hxs.scheduler.config.GlobalConfig;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.*;
import java.nio.charset.Charset;

public class TaskJob implements Job {
    private GlobalConfig globalConfig = BeanHelper.getBean(GlobalConfig.class);


    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap jobDataMap = context.getMergedJobDataMap();
        String cmd = jobDataMap.getString(KeyConstant.CMD);
        System.out.println(cmd);
    }

    public static void main(String[] args) {
        try {
            Process process = Runtime.getRuntime().exec("cmd /c dir", null, new File("F:/hoxiansen"));
            InputStream in = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "GBK"));
            reader.lines().forEach(System.out::println);
            in.close();
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
