package com.hxs.scheduler.mapper;

import com.hxs.scheduler.entity.Script;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.springframework.stereotype.Component;

@Component
public interface ScriptMapper {
//    @Insert("insert into `script` (name, path, params) value (#{name},#{path},#{params})")
//    void insert(@Param("name") String name, @Param("path") String path,@Param("params") String params);
    @Insert("insert into `script` (name, path, params) " +
            "value (#{name},#{path},#{params})")
    void insert(Script script);
}
