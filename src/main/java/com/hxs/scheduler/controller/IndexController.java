package com.hxs.scheduler.controller;

import com.hxs.scheduler.bean.ScriptParams;
import com.hxs.scheduler.common.bean.ResMsg;
import com.hxs.scheduler.common.util.BeanHelper;
import com.hxs.scheduler.config.Config;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {
    @GetMapping("/")
    public String index() {
        return "index/test";
    }

    @RequestMapping("/test")
    @ResponseBody
    public ResMsg test(ScriptParams[] scriptParams) {
        Config bean = BeanHelper.getBean(Config.class);
        System.out.println(bean.getLogLocation());
        return ResMsg.success(scriptParams);
    }
}
