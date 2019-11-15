package com.hxs.scheduler.controller;

import com.hxs.scheduler.common.bean.ResMsg;
import com.hxs.scheduler.common.util.BeanHelper;
import com.hxs.scheduler.config.Config;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/")
    public String index() {
        return "upload_script";
    }

    @GetMapping("/test")
    public ResMsg test() {
        Config bean = BeanHelper.getBean(Config.class);
        System.out.println(bean.getLogLocation());
        return ResMsg.success();
    }
}
