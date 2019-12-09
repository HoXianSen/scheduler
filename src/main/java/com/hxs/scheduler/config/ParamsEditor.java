package com.hxs.scheduler.config;

import com.alibaba.fastjson.JSON;
import com.hxs.scheduler.bean.Param;
import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;

public class ParamsEditor extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if(StringUtils.hasText(text)){
            setValue(JSON.parseArray(text, Param.class));
        }else{
            setValue(null);
        }
    }

    @Override
    public String getAsText() {
        Object value = getValue();
        if(value == null){
            return "";
        }
        return JSON.toJSONString(value);
    }
}
