package org.java.shell.Controller;

import org.java.shell.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class CarController {
    @Autowired
    private CarService ser;
    @Autowired
    private CarbrandDaoService cs;
    @Autowired
    private DiscountService dis;
    @Autowired
    private TelephonService tel;
    @Autowired
    private UserService user;

    @RequestMapping("/recommend")//主页的商家推荐
    @ResponseBody
    public List<Map> recommend(HttpSession ses){
        List<Map> rec = user.getUser();
        return rec;
    }

    @RequestMapping("cartj")
    @ResponseBody
    public List<Map> cartj(){
        return imgesList(ser.recommend());
    }


    @RequestMapping("/tj")
    @ResponseBody
    public Map tj(HttpSession ses){
        Map discount = dis.getDiscount();
        List<Map> recommendList = ser.recommend();
        imgesList(recommendList);
        Map map=new HashMap();
        map.put("discount",discount);
        map.put("recommendList",recommendList);
        return map;
    }
    //点击多条件查询
    @RequestMapping("/find")
    @ResponseBody
    public Map find(String aaa, String name, String moneys, String zuidi,String zuigao,String zuiduan,String zuishao,Integer size,Integer count,HttpSession ses){
        System.out.println(moneys+"name");
        String money="";
        String ms="";
        if (moneys!=null&&moneys!=""){
            switch (moneys){
                case "5":
                    money="0";
                    ms="5";
                    break;
                case "10":
                    money="5";
                    ms="10";
                    break;
                case "20":
                    money="10";
                    ms="20";
                    break;
                case "50":
                    money="20";
                    ms="50";
                    break;
            }
        }
        Map map =new HashMap();
        Integer zts=ser.cou(aaa,name,money,ms,zuidi,zuigao,zuiduan,zuishao);
        Integer zys=(zts-1)/5+1;
        if (size==null||size==-1){
            size=1;
        } else {
            size++;
        }
        Integer dqy=(size-1)*5;

        System.out.println(size+"000000000000000000");
        ses.setAttribute("size",size);
        List<Map> query = ser.findQuery(aaa,name,money,ms,zuidi,zuigao,zuiduan,zuishao,dqy,5);
        ses.setAttribute("maps",query);
        List<Map> list = imgesList(query);
        List<Map> lists =new ArrayList<>();
        map.put("size",size);
        Map discount = dis.getDiscount();
        if (!list.isEmpty()){
            for (Map map1 : list) {
                if (map1.get("carTist")!=null){
                    Double a=Double.parseDouble(map1.get("carTist").toString())+Double.parseDouble(discount.get("discountMoney").toString());
                    map1.put("carTist",a);
                    lists.add(map1);
                }
            }
        }
        map.put("list",lists);
        System.out.println(map);
        return map;
    }

    //主页点击查询
    @RequestMapping("/find2")
    public String find2(String aaa, String name, String moneys, String zuidi,String zuigao,String zuiduan,String zuishao,Integer size,Integer count,HttpSession ses){
        System.out.println(name+"find2");
        List<Map> carbrand = cs.getAll();//查询所有品牌
        ses.setAttribute("carbrand",carbrand);
        String money="";
        String ms="";
        /*if (moneys.equals("")&&moneys!=""){
            switch (moneys){
                case "5":
                    money="0";
                    moneys="5";
                    break;
                case "10":
                    money="5";
                    moneys="10";
                    break;
                case "20":
                    money="10";
                    moneys="20";
                    break;
                case "50":
                    money="20";
                    moneys="50";
                    break;
            }
        }*/
        Map map =new HashMap();
        Integer zts=ser.cou(aaa,name,money,ms,zuidi,zuigao,zuiduan,zuishao);
        Integer zys=(zts-1)/5+1;
        if (size==null||size==-1){
            size=1;
        }else if(size==zys){
            size=zys;
        } else {
            size++;
        }
        Integer dqy=(size-1)*5;
        System.out.println(size+"11111");
        ses.setAttribute("size",size);
        List<Map> query = ser.findQuery(aaa,name,money,moneys,zuidi,zuigao,zuiduan,zuishao,dqy,5);
        ses.setAttribute("maps",query);
        List<Map> list = imgesList(query);
        List<Map> lists =new ArrayList<>();
        Map discount=dis.getDiscount();
        if (!list.isEmpty()){
            for (Map map1 : list) {
                Double a=Double.parseDouble(map1.get("carTist").toString())+Double.parseDouble(discount.get("discountMoney").toString());
                map1.put("carTist",a);
                lists.add(map1);
            }
        }
        map.put("list", lists);
        map.put("size", size);
        ses.setAttribute("lists",lists);
        ses.setAttribute("status", "000");
        return "filter";
    }

    @RequestMapping("/xq")
    public String xq(Integer cid,HttpSession ses){
        Map<String,Object> xqlist = ser.xq(cid);
        String[] xqimges=null;
        xqimges=xqlist.get("carImges").toString().split(",");
        Map<String,Object> mm=new HashMap<>();

        mm.put("xqimges",xqimges);
        Map discount = dis.getDiscount();
        mm.put("dis", discount);
        if (xqlist.get("carState").equals(1)){
            xqlist.put("carState", "自动挡");
        }else {
            xqlist.put("carState", "非自动");
        }
        mm.put("xqlist",xqlist);
        ses.setAttribute("mm",mm);
        List<Map> phone = tel.getPhone();
        Random r=new Random();
            int num=r.nextInt(phone.size());
        Map map = phone.get(num);
        ses.setAttribute("telPhone",map);
        return "CarDetails";
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
