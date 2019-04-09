package org.java.merchant.Controller;

import org.java.merchant.service.CarBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/insue")
public class issueController {
    @Autowired
    private CarBrandService carBrandService;
    @Autowired
    private HttpSession session;

    @RequestMapping("/issueLoad")
    public String issue(){
        session.setAttribute("carBrandList", carBrandService.selectCarBrand());
        System.out.println(carBrandService.selectCarBrand());
        return "demo";
    }
}
