package com.hxs.scheduler.controller;

import com.hxs.scheduler.common.bean.ResMsg;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/script")
public class ScriptController {
    @PostMapping("/upload")
    public ResMsg upload(@RequestBody MultipartFile scriptFile) {
        if(scriptFile.isEmpty()){
            return ResMsg.normalFail("文件为空");
        }
        String scriptName = scriptFile.getName();
        return ResMsg.SUCCESS;
    }
}
