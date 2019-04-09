package org.java.shell.service.impl;

import org.java.shell.dao.CarDao;
import org.java.shell.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    public CarDao dao;

    @Override
    public List<Map> recommend() {
        return dao.recommend();
    }

    @Override
    public List<Map> find(Integer one,Integer two) {
        return dao.find(one,two);
    }

    @Override
    public List<Map> find2(String name) {
        return dao.find2(name);
    }

    @Override
    public List<Map> findQuery(String aaa, String name,String money,String moneys,String zuidi, String zuigao, String zuiduan, String zuishao,Integer size,Integer count) {
        return dao.findQuery(aaa,name,money,moneys,zuidi,zuigao,zuiduan,zuishao,size,count);
    }

    @Override
    public Integer cou(String aaa, String name, String money,String moneys, String zuidi, String zuigao, String zuiduan, String zuishao) {
        return dao.cou(aaa,name,money,moneys,zuidi,zuigao,zuiduan,zuishao);
    }

    @Override
    public Map xq(Integer cid) {
        return dao.xq(cid);
    }
}
