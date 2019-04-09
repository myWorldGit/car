package org.java.merchant.Controller;

import org.java.merchant.dao.CarBrandDao;
import org.java.merchant.service.CarBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/car")
public class MerCarController {
    @Autowired
    private CarBrandService carBrandService;

    @RequestMapping("/carAdd")
    @ResponseBody
    private String carAddInsert( Model model,@RequestParam("file") MultipartFile[] files, HttpServletRequest request){
        System.err.println(((Map)request.getSession().getAttribute("user")).get("userID"));
        StringBuilder carImges=new StringBuilder("");
        System.out.println("/"+request.getParameter("carState"));
        Map<String,Object>carMap=new HashMap<>();
        carMap.put("carPrice",request.getParameter("carPrice"));
        carMap.put("carTist",request.getParameter("carTist"));
        carMap.put("carApply",request.getParameter("carApply"));
        carMap.put("carSite",request.getParameter("carSite"));
        carMap.put("carState",request.getParameter("carState"));
        carMap.put("carTravel",request.getParameter("carTravel"));
        carMap.put("carName",request.getParameter("carName"));
        carMap.put("carDetails",request.getParameter("carDetails"));
        carMap.put("carPhone",request.getParameter("carPhone"));
        carMap.put("carTime",request.getParameter("carTime"));
        carMap.put("carPrice",request.getParameter("carPrice"));
        System.err.println(((Map)request.getSession().getAttribute("user")).get("userID"));
        carMap.put("userID",((Map)request.getSession().getAttribute("user")).get("userID"));
        carMap.put("carLetout",request.getParameter("carLetout"));
        carMap.put("carBrandID",request.getParameter("carBrandID"));
        //request.getSession().getAttribute("")
        //   insert into car values(NULL,#{carImges},#{},#{},#{},#{},#{},#{},#{},#{},#{},#{},now(),#{},#{},#{})

        String[] str = request.getParameterValues("imageName");
        if (str!=null){
        for (int i=0;i<str.length;++i){
           BASE64Decoder decoder = new BASE64Decoder();
           // Base64解码
           System.out.println(str);
           System.out.println("==");
           byte[] imageByte = null;
           try {
               imageByte = decoder.decodeBuffer(str[i]);
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
           carImges.append(","+files1);
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
           }
          }
        }
        if (carImges.length()>0){
        carMap.put("carImges", carImges.toString().substring(1,carImges.length()));
        }
        System.out.println(carMap);
        carBrandService.insertCar(carMap);
        return "1";
    }
    @RequestMapping("/tup")
    private String tup(){
        return "tup";
    }


}
