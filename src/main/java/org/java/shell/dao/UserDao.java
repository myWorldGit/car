package org.java.shell.dao;

import org.apache.ibatis.annotations.Mapper;

import javax.validation.constraints.Max;
import java.util.List;
import java.util.Map;

@Mapper
public interface UserDao {
    public List<Map> getUser();
    public int cou();
}
