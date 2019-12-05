package com.hxs.scheduler.controller;

import com.hxs.scheduler.common.bean.ResMsg;
import com.hxs.scheduler.config.Config;
import com.hxs.scheduler.entity.Script;
import com.hxs.scheduler.mapper.ScriptMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RequestMapping("/script")
@Controller
@Slf4j
public class ScriptController {
    @Resource
    private Config config;
    @Resource
    private ScriptMapper scriptMapper;

    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("script/index");
        List<Script> scripts = scriptMapper.selectAllScripts();
        modelAndView.addObject("scripts", scripts);
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    @ResponseBody
    public ResMsg delete(@PathVariable("id") int id) {
        if (id <= 0) {
            return ResMsg.fail("参数错误");
        }
        scriptMapper.deleteById(id);
        return ResMsg.ok();
    }

    @PostMapping("/upload")
    @ResponseBody
    public ResMsg upload(@RequestParam MultipartFile script, @RequestParam String scriptParams) {
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
        scriptMapper.insert(filename, scriptParams);
        return ResMsg.ok(filename, scriptParams);
    }

    @GetMapping("/upload")
    public String upload() {
        return "script/upload";
    }
}
