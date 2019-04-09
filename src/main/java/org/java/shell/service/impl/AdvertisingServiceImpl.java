package org.java.shell.service.impl;


import org.java.shell.dao.AdvertisingDao;
import org.java.shell.service.AdvertisingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AdvertisingServiceImpl implements AdvertisingService {

    @Autowired
    private AdvertisingDao dao;

    @Override
    public List<Map> findAll() {
        return dao.findAll();
    }
}
