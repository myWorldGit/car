package org.java.shell.service.impl;

import org.java.shell.dao.TelephonDao;
import org.java.shell.service.TelephonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TelephonServiceImpl implements TelephonService {

    @Autowired
    private TelephonDao dao;

    @Override
    public List<Map> getPhone() {
        return dao.getPhone();
    }
}
