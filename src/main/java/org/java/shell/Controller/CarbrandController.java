package org.java.shell.Controller;

import org.java.shell.service.CarService;
import org.java.shell.service.CarbrandDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/carbrand")
public class CarbrandController {

    @Autowired
    private CarbrandDaoService ser;

    @Autowired
    private CarService carService;

    @RequestMapping("/getAll")
    public String getAll(HttpSession sess){
        /*List<Map> carbrand = ser.getAll();//查询所有品牌
        sess.setAttribute("carbrand",carbrand);*/
        List<Map> recommend = carService.recommend();
        /*List<Map> list = imgesList(recommend);
        sess.setAttribute("maps",list);*/
        return "filter";
    }
    @RequestMapping("/tiaozhuan")
    public String tiaozhuan(){
        return "filter";
    }

    private List<Map> imgesList(List<Map> list){
        List<Map>list1Imges=new ArrayList<>();
        for (Map m: list) {
            String[]imges=m.get("carImges").toString().split(",");
            m.put("carImges", imges[0]);
            list1Imges.add(m);
        }
        return  list1Imges;
    }
}
