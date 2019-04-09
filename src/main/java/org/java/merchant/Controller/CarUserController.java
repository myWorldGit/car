package org.java.merchant.Controller;

import org.apache.catalina.User;
import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.java.merchant.service.CarBrandService;
import org.java.shell.service.CarService;
import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Controller
@RequestMapping("/gocar")

/**
 * 个人汽车表
 */
public class CarUserController {

    @Autowired
    private HttpSession session;

    @Autowired
    private CarBrandService carBrandService;
    @RequestMapping("/jiazai")
    private String jiazai(){
        return "jiazai";
    }

    @RequestMapping("/carList")
    public String ListCarUser(HttpSession session){
        //获取用户id
        String userID=((Map)session.getAttribute("user")).get("userID")+"";
        System.out.println("userID:"+userID);

        List<Map>list=carBrandService.carListUserInio(userID, 1, 7, "");
        System.out.println(list);
        session.setAttribute("carlist",imgesList(list));
        return  "list";
    }
    @RequestMapping("/userCaer")
    private String userCar(String userID, HttpServletRequest request){
        request.setAttribute("userID",userID );
        System.out.println("userID:"+userID);
        session.setAttribute("carCount",carBrandService.countCar(userID));
        List<Map>list=carBrandService.carListUserInio(userID, 1, 7, "");
        System.out.println(list.size());
        session.setAttribute("carlist",imgesList(list));
        return "userCarlist";
    }

    @RequestMapping("/carDetails")
    private String carDetails(String carID){
        Map car=carBrandService.carDetails(carID);
        System.out.println(car);
        session.setAttribute("carDemo",car);
        session.setAttribute("carBrandList", carBrandService.selectCarBrand());
        return  "demoUpdate";
    }

    @ResponseBody
    @RequestMapping("/carAjax")
    private  List<Map> carAjax(@RequestParam Map<String,String>map, HttpSession session){
        System.out.println("size1:"+map.get("size"));
        String userID="";
        if (map.get("userID")==null){
            userID=((Map)session.getAttribute("user")).get("userID")+"";
        }else{
            System.out.println(userID);
            userID=map.get("userID").toString();
        }
        System.out.println(map);
        List<Map>list=carBrandService.carListUserInio(userID, Integer.parseInt(map.get("size")), 7, map.get("valkey"));
        System.err.println(list);
        return imgesList(list);
    }
    @ResponseBody
    @RequestMapping("/carupload")
    private String carupload(@RequestParam Map map){
        System.err.println(map);
      int stati=carBrandService.carUpdateRservice(map);
      if (stati!=0){
          return  "1";
      }
        return "0";
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

     @RequestMapping("/deletecarID")
     @ResponseBody
     private String deletecarID(String carID){
       int stati=carBrandService.deletecar(carID);
       return "success";
     }


}
