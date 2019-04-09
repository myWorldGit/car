package org.java.merchant.Controller;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.java.merchant.dao.UserInsertDao;
import org.java.merchant.service.CarBrandService;
import org.java.merchant.service.UserInsertService;
import org.java.merchant.util.HttpUtils;
import org.java.merchant.util.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/login")
public class regisCpntroller {
    @Autowired
    private CarBrandService carBrandService;
    @Autowired
     private HttpSession session;
    @Autowired
    private UserInsertService userInsertService;

@RequestMapping("/regis")
    private  String loginLoad()throws Exception{
    //int i=2/0;
           return "register";
    }
    @RequestMapping("/debarkLoad")
    public  String debarkLoad(){
    return  "debark";
    }
    @RequestMapping("/personalLoad")
    public  String personalload(){
        Map user=(Map)session.getAttribute("user");
        System.out.println("user:"+user);
        if (user==null){
            return "redirect:/login/debarkLoad";
        }
        System.out.println(carBrandService.countCar(user.get("userID")+""));
        session.setAttribute("carCount",carBrandService.countCar(user.get("userID")+""));
         return "personal";
    }
    //个人设置
    @RequestMapping("/instalLoad")
    public  String install(){
        return "install";
    }
    //ajax短信

    @ResponseBody
    @RequestMapping("/PhonedateSoce")
    public  String PhonedateSoce(@RequestParam Map<String,Object>map){
    //电话存如Session
    session.setAttribute(map.get("userPhone").toString(),map.get("dateSoce").toString());
    //调用短信接口
       String code="【1+1好车直卖】欢迎入驻1+1好车直卖您的验证码为:"+map.get("dateSoce").toString()+",请在5分钟内完成注册";
       String stite=note(map.get("userPhone").toString(),code);
        if ("0".equals("1")) {
            return "0";
        }
           return "1";
    }

    @RequestMapping("/phoneIf")
    @ResponseBody
    private String phone(String phone){
        System.out.println(phone);
        boolean pandphone=userInsertService.userPhoneInfoService(phone);
        System.out.println("phone="+pandphone);
        if (pandphone){
            //有内容
            return "1";
        }else {
            return "0";
        }
    }

    @RequestMapping("/dateSoceVer")
    @ResponseBody
    private  String dateSoceVer(@RequestParam Map<String,Object>map){
            String dateSoce=session.getAttribute(map.get("userPhone").toString()).toString();
             map.put("userImges","user-logo-003.png");
            if (dateSoce.equals(map.get("dateSoce"))){
                if (userInsertService.insertUserService(map)==0){
                    return "0";
                }
                return "1";
            }   else {
                return "0";
            }
            }

            @ResponseBody
            @RequestMapping("/carbrandSelect")
            private List<Map> carbrandSelect(){
                return carBrandService.selectCarBrand();
            }

            //登陆
    @ResponseBody
    @RequestMapping("/loginUser")
    private String userPwd(String userPhone,String userPwd){
         Map usermap=userInsertService.userLogin(userPhone,userPwd);
         if (usermap!=null){//登陆成功
             if(Integer.parseInt(usermap.get("userState").toString())==1)return "0";
             session.setAttribute("user", usermap);//存入session
             if (Integer.parseInt(usermap.get("userState")+"")==1){
                 return  "0";//登陆失败账号密码错误
             }
             return "1";//登陆成功
         }else{
             return  "0";//登陆失败账号密码错误
         }
    }
    @RequestMapping("/urlReturn")
    private  String UrlReturn(String Url){
        System.err.println(Url);
    return  Url;
    }
    @RequestMapping("/userPhone")
    @ResponseBody
    private String userPhone(String phone,String code){
    String codesession=session.getAttribute("coad"+phone)+"";
        System.err.println("codesession"+codesession);
        System.out.println("code"+code);
        if (!codesession.equals(code)){
            return "3";
        }
        Map user=userInsertService.userPhoneVal(phone);
        if (user!=null){
            System.out.println("userState:"+user.get("userState"));
            if(Integer.parseInt(user.get("userState").toString())==1)
            { System.out.println("进入了");
                return "0";
            }
            session.setAttribute("user",user);
           return "1";
       }
         return "0";
    }

    /**
     *
     * @param phone 电话
     * @param coad 随机数
     * @return
     */
    @RequestMapping("/coadUser")
    @ResponseBody
    private String carUser(String phone,String coad){
        System.err.println(coad);
        session.setAttribute("coad"+phone,coad);
        session.setMaxInactiveInterval(5*60);
        String coadint="【1+1好车直卖】欢迎登陆1+1好车直卖您的验证码为:"+coad+",请在5分钟内完成登陆";
        note(phone,coadint);//电话通知
        return "1";
    }
    /**
     *
     * @param phone 电话
     * @param coad   验证码
     * @return
     */
    private String note(String phone,String coad){
        String host = "https://cxkjsms.market.alicloudapi.com";
        String path = "/chuangxinsms/dxjk";
        String method = "POST";
        String appcode = "60856d70dc8b4e88873a24da173554d6";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("content", coad);
        querys.put("mobile", phone);
        Map<String, String> bodys = new HashMap<>();
        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            //System.out.println(response.toString());
            //获取response的body
            System.out.println(EntityUtils.toString(response.getEntity()));
            return "1";
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
    }

    /**
     *
     * 退出
     */
    @RequestMapping("/exit")
    private  String exit(){
        session.removeAttribute("user");//删除
        return "redirect:/login/debarkLoad";
    }
    /**
     * 上传图片
     * file :base64文件
     */
    @RequestMapping("/uploadImgers")
    @ResponseBody
    private  String uploadImgers(String file) {
        System.err.println(file);
        BASE64Decoder decoder = new BASE64Decoder();
        // Base64解码
        byte[] imageByte = null;
        try {
            imageByte = decoder.decodeBuffer(file);
            for (int j = 0; j < imageByte.length; ++j) {
                if (imageByte[j] < 0) {// 调整异常数据
                    imageByte[j] += 256;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 生成文件名
        String files1 = new SimpleDateFormat("yyyyMMddHHmmssSSS")
                .format(new Date())
                + (new Random().nextInt(9000) % (9000 - 1000 + 1) + 1000)
                + ".png";
        // 生成文件路径
        String filename = "/home/images/"+ files1;
        try {
            // 生成文件
            File imageFile = new File(filename);
            imageFile.createNewFile();
            if(!imageFile.exists()){
                imageFile.createNewFile();
            }
            OutputStream imageStream = new FileOutputStream(imageFile);
            imageStream.write(imageByte);
            imageStream.flush();
            imageStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
        Map<String,Object> map=new HashMap<>();
        map.put("userImges", files1);
        map.put("userID",((Map)session.getAttribute("user")).get("userID"));
        int zhi= userInsertService.updateUser(map);
        Map userMap=new HashMap();
        userMap=(Map)session.getAttribute("user");
        userMap.put("userImges",files1 );
        session.setAttribute("user",userMap);
        System.out.println(zhi);
        return files1;
    }

    //修改个人信息
    @RequestMapping("/userUpdateSet")
    @ResponseBody
    private String userUpdateSet(@RequestParam Map<String,Object> map){
        int state=userInsertService.updateUser(map);
        //更新本地
        session.setAttribute("user",userInsertService.userPhoneVal(((Map)session.getAttribute("user")).get("userPhone")+""));
        System.out.println(session.getAttribute("user"));
        if (state!=0){
            return "success";
        }
        return "error";
    }

}
