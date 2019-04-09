package org.java.merchant.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface CarBrandDao {
    List<Map>selectCarBanList();//查询全部
    int insertCar(Map<String,Object> map);
    int countCar(@Param("userID")String userID);
    //查询用户上架的汽车

    /**
     *
     * @param userID 用户
     * @param size 当前
     * @param count 总
     * @return
     */
    List<Map>carListUserInio(@Param("userID") String userID,@Param("size") int size,@Param("count") int count,@Param("valKey") String valKey);
    Map carDetails(@Param("carID") String carID);
    int carUpdateR(Map map);
    int deletecar(@Param("carID") String carID);
}
