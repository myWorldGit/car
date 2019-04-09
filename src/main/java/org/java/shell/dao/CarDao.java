package org.java.shell.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import javax.validation.constraints.Size;
import java.util.List;
import java.util.Map;

@Mapper
public interface CarDao {
    public List<Map> recommend();//查询主页的推荐
    public List<Map> find(@Param("one") Integer one,@Param("two") Integer two );//根据价格查询
    public List<Map> find2(@Param("name") String name);//根据品牌名查
    public List<Map> findQuery(@Param("aaa") String aaa,@Param("name") String name,@Param("money") String money,@Param("moneys") String moneys,@Param("zuidi") String zuidi,@Param("zuigao") String zuigao,@Param("zuiduan") String zuiduan,@Param("zuishao") String zuishao , @Param("size") Integer Size,@Param("count") Integer count);
    public Integer cou(@Param("aaa") String aaa,@Param("name") String name,@Param("money") String money,@Param("moneys") String moneys,@Param("zuidi") String zuidi,@Param("zuigao") String zuigao,@Param("zuiduan") String zuiduan,@Param("zuishao") String zuishao);
    public Map xq(@Param("cid") Integer cid);
}
