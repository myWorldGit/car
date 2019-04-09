package org.java.merchant.service.impl;
import org.java.merchant.dao.CarBrandDao;
import org.java.merchant.service.CarBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.util.List;
import java.util.Map;

@Service
public class CarBrandServiceImpl implements CarBrandService {

    @Autowired
    private CarBrandDao dao;
    @Autowired
    private HttpSession session;

    @Override
    public int deletecar(String carID) {
        return dao.deletecar(carID);
    }

    @Override
    public int carUpdateRservice(Map map) {
        return dao.carUpdateR(map);
    }

    @Override
    public Map carDetails(String carID) {
        return dao.carDetails(carID);
    }

    @Override
    public List<Map> carListUserInio(String userID, int size, int count, String valKey) {
        System.out.println("session:"+(size-1)*count+1);
        session.setAttribute("size",size);
        if ((size-1)*count+1==1){
            return dao.carListUserInio(userID, 0, count, valKey);
        }else{
            return dao.carListUserInio(userID,(size-1)*count+1 , count, valKey);
        }
    }

    @Override
    public int countCar(String userID) {
        return dao.countCar(userID);
    }

    @Override
    public int insertCar(Map<String, Object> map) {
        return dao.insertCar(map);
    }

    @Override
    public List<Map> selectCarBrand() {
      return  dao.selectCarBanList();
    }
}
