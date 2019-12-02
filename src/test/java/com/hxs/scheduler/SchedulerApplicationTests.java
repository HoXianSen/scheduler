package com.hxs.scheduler;

import com.hxs.scheduler.entity.Script;
import com.hxs.scheduler.mapper.ScriptMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SchedulerApplicationTests {
    @Resource
    private ScriptMapper scriptMapper;
    @Test
    public void contextLoads() {
        Script script = new Script();
        script.setName("name");
        script.setParams("");
        script.setParams("path");
//        scriptMapper.insert("name", "path", "");
        scriptMapper.insert(script);
    }

}
