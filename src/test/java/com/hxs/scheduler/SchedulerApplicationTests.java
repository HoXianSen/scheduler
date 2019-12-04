package com.hxs.scheduler;

import com.alibaba.fastjson.JSON;
import com.hxs.scheduler.entity.Script;
import com.hxs.scheduler.mapper.ScriptMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SchedulerApplicationTests {
    @Resource
    private ScriptMapper scriptMapper;
    @Test
    public void contextLoads() {
        List<Script> scripts = scriptMapper.selectAllScripts();
        System.out.println(JSON.toJSONString(scripts));
    }

}
