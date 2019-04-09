package org.java.shell.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TelephonDao {
    public List<Map> getPhone();
}
