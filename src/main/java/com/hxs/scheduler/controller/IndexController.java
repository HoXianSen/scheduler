package com.hxs.scheduler.controller;

import com.hxs.scheduler.common.bean.ResMsg;
import com.hxs.scheduler.common.util.BeanHelper;
import com.hxs.scheduler.config.GlobalConfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    @GetMapping("/")
    public ResMsg index() {
        return ResMsg.SUCCESS;
    }

    @GetMapping("test")
    public ResMsg test() {
        GlobalConfig bean = BeanHelper.getBean(GlobalConfig.class);
        System.out.println(bean.getAbsLogDir());
        return ResMsg.SUCCESS;
    }
}
