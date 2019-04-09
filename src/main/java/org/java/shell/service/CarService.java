package org.java.shell.service;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CarService {
    public List<Map> recommend();
    public List<Map> find(Integer one,Integer two);
    public List<Map> find2(String name);
    public List<Map> findQuery(String aaa, String name,String money,String moneys,String zuidi, String zuigao, String zuiduan, String zuishao,Integer size,Integer count);
    public Integer cou(String aaa, String name,String money,String moneys,String zuidi, String zuigao, String zuiduan, String zuishao);
    public Map xq(@Param("cid") Integer cid);
}
