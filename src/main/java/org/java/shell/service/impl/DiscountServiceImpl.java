package org.java.shell.service.impl;

import org.java.shell.dao.DiscountDao;
import org.java.shell.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.DispatcherServlet;

import java.util.Map;

@Service
public class DiscountServiceImpl implements DiscountService {

    @Autowired
    private DiscountDao dao;

    @Override
    public Map getDiscount() {
        return dao.getDiscount();
    }
}
