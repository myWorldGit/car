package org.java.shell.service.impl;

import org.java.shell.dao.LoginMapper;
import org.java.shell.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginMapper login;

    @Override
    public Map loginSub(Map map) {
        return login.loginSub(map);
    }
}
