package org.java.shell.service.impl;

import org.java.shell.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements org.java.shell.service.UserService {

    @Autowired
    private UserDao dao;
    @Override
    public List<Map> getUser() {
        return dao.getUser();
    }

    @Override
    public int cou() {
        return dao.cou();
    }
}
