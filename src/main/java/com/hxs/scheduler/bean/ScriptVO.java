package com.hxs.scheduler.bean;

import com.alibaba.fastjson.JSON;
import com.hxs.scheduler.entity.Script;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ScriptVO {
    public ScriptVO(Script script) {
        id = script.getId();
        name = script.getName();
        params = JSON.parseArray(script.getParams(), Param.class);
    }

    private int id;
    private String name;
    private List<Param> params;
}
