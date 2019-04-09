package org.java.shell.Controller;

import org.java.shell.service.CarbrandDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class FilterController {
    @Autowired
    private CarbrandDaoService ser;

    @RequestMapping("/tiao")
    public String tiao(HttpSession ses){
        Object status = ses.getAttribute("status");
        System.out.println(status);
        Object lists = ses.getAttribute("lists");
        System.out.println(lists);
            ses.setAttribute("status",11);
            ses.removeAttribute("lists");
        List<Map> all = ser.getAll();
        ses.setAttribute("carbrand", all);
        return "redirect:/carbrand/getAll";
    }
}
