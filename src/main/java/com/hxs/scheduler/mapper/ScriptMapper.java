package com.hxs.scheduler.mapper;

import com.hxs.scheduler.entity.Script;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ScriptMapper {
    @Insert("insert into `script` (name, params) value (#{name},#{params})")
    void insert(@Param("name") String name, @Param("params") String params);
    @Select("select * from `script`")
    List<Script> selectAllScripts();
    @Select("select * from `script` where `id`=#{id}")
    Script selectById(@Param("id") int id);
    @Delete("delete from `script` where `id` = #{id}")
    void deleteById(@Param("id") int id);
}
