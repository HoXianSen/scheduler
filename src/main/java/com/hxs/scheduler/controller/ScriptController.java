package com.hxs.scheduler.controller;

import com.hxs.scheduler.bean.Param;
import com.hxs.scheduler.common.bean.ResMsg;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/script")
@Controller
public class ScriptController {
    @GetMapping("/")
    public String index() {
        return "upload_script";
    }

    @PostMapping("/upload")
    @ResponseBody
    public ResMsg upload(@RequestParam MultipartFile script, ArrayList<Param> params) {
        if (script.isEmpty()) {
            return ResMsg.fail("文件为空");
        }
        String name = script.getName();
        System.out.println(name);
        return ResMsg.success();
    }
}
