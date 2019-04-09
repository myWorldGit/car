package org.java.shell.service.impl;

import org.java.shell.dao.CarbrandDao;
import org.java.shell.service.CarbrandDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CarbrandDaoServiceImpl implements CarbrandDaoService {

    @Autowired
    private CarbrandDao dao;

    @Override
    public List<Map> getAll() {
        return dao.getAll();
    }
}
