package org.java.merchant.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.awt.*;
import java.util.List;
import java.util.Map;
@Mapper
public interface UserInsertDao {
    Map userPhoneInfo(@Param("userPhone") String userPhone);
    int insertUser(Map map);
    Map userLogin(@Param("userPhone") String userPhone,@Param("userPwd")String userPwd);
    Map userPhoneVal(@Param("userPhone")String userPhone);
    int updateUser(Map map);
}
