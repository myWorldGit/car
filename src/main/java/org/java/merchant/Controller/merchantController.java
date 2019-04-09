package org.java.merchant.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/merchant")
public class merchantController {
    @RequestMapping("/load")
    public  String loadmerchant(){
        return "index";
    }
    @RequestMapping("/index1")
    public  String loadmerchant1(){
        return "index1";
    }


}
