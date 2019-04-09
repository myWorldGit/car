package org.java.shell.Controller;

import org.java.shell.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserServiceImpl ser;

    @RequestMapping("/getUser")
    public String getUser(HttpSession ses){
        List<Map> user = ser.getUser();
        ses.setAttribute("user",user);
        return "index";
    }

    @RequestMapping("/cou")
    @ResponseBody
    public Integer cou(){
        int cou = ser.cou();
        return cou;
    }
}
