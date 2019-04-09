package org.java.shell.service;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

public interface LoginService {
    Map loginSub(Map map);
}
