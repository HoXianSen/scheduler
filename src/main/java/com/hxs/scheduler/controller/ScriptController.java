package com.hxs.scheduler.controller;

import com.hxs.scheduler.bean.Param;
import com.hxs.scheduler.common.bean.ResMsg;
import com.hxs.scheduler.config.Config;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@RequestMapping("/script")
@Controller
@Slf4j
public class ScriptController {
    @Resource
    private Config config;

    @GetMapping("/")
    public String index() {
        return "upload_script";
    }

    @PostMapping("/upload")
    @ResponseBody
    public ResMsg upload(@RequestParam MultipartFile script, Param[] params) {
        if (script == null || script.isEmpty()) {
            return ResMsg.fail("上传文件为空");
        }
        String filename = script.getOriginalFilename();
        String scriptLocation = config.getScriptLocation();
        File scriptFile = new File(scriptLocation + filename);
        try (FileOutputStream fos = new FileOutputStream(scriptFile);
             InputStream is = script.getInputStream()) {
            int read = 0;
            while ((read = is.read()) != -1) {
                fos.write(read);
            }
        } catch (IOException e) {
            log.error("上传文件写入失败", e);
            return ResMsg.fail("上传文件写入失败");
        }
        return ResMsg.success(scriptFile.getAbsolutePath());
    }
}
