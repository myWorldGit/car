package org.java.shell.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface LoginMapper {
    Map loginSub(Map map);
}
