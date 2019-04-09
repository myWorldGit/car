package org.java.shell.Controller;

import org.java.shell.service.AdvertisingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class AdvertisingController {

    @Autowired
    private AdvertisingService service;

    @RequestMapping("/aa")
    public String findAll(HttpSession ses){
        List<Map> all = service.findAll();
        ses.setAttribute("list",all);
        return "index";
    }


}
