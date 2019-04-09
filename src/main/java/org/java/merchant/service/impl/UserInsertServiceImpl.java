package org.java.merchant.service.impl;

import org.java.merchant.dao.UserInsertDao;
import org.java.merchant.service.UserInsertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
@Service
public class UserInsertServiceImpl implements UserInsertService {
    @Autowired
    private UserInsertDao userInsertDao;

    @Override
    public int updateUser(Map map) {
        System.out.println(map);
      return   userInsertDao.updateUser(map);
    }

    @Override
    public Map userPhoneVal(String userPhone) {
        return userInsertDao.userPhoneVal(userPhone);
    }
    @Override
    public Map userLogin(String userPhone, String userPwd) {
        return userInsertDao.userLogin(userPhone,userPwd);
    }

    @Override
    public int insertUserService(Map map) {
        return  userInsertDao.insertUser(map);
    }

    @Override
    public boolean userPhoneInfoService(String phone) {
        System.out.println(phone);
        Map map=userInsertDao.userPhoneInfo(phone);
        System.out.println("map="+map);
        if (map!=null){
            return true;
        }else {
            return false;
        }
    }
}
