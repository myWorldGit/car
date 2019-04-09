package org.java.merchant.service;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CarBrandService {
        List<Map>selectCarBrand();
        int insertCar(Map<String,Object> map);
        int countCar(@Param("userID")String userID);
        List<Map>carListUserInio(String userID,int size,int count,String valKey);
        Map carDetails(@Param("carID") String carID);
        int carUpdateRservice(Map map);
        int deletecar(@Param("carID") String carID);

}
