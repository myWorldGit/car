package org.java.merchant.service;

import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface UserInsertService {
    boolean userPhoneInfoService(String phone);
    int insertUserService(Map map);
    //手机号+密码登陆
    Map userLogin(@Param("userPhone") String userPhone,@Param("userPwd")String userPwd);
    Map userPhoneVal(@Param("userPhone")String userPhone);
    //修改
    int updateUser(Map map);
}
