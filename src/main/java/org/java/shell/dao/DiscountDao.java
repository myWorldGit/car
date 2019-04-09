package org.java.shell.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface DiscountDao {
    public Map getDiscount();
}
