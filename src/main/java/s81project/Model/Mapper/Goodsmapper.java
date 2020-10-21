package s81project.Model.Mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import s81project.Model.pojo.Goods;
import s81project.Model.pojo.User;

import java.util.List;
@Mapper
@Repository
public interface Goodsmapper {
    @Select("select * from goodsinfo")
    @Results({@Result(column = "Goods_id",property = "goods_id"),
            @Result(column = "Goods_name",property = "goods_name"),
            @Result(column = "Goods_price",property = "goods_price"),
            @Result(column = "goods_type",property = "goods_type"),
            @Result(column = "goods_state",property = "goods_state"),
            @Result(column = "Goods_stock",property = "goods_stock"),
            @Result(column = "goods_sell",property = "goods_sell"),
            @Result(column = "goods_brand",property = "goods_brand"),
            @Result(column = "goods_img",property = "goods_img"),
            @Result(column = "goods_discrible",property = "goods_discrible"),
    })
     List<Goods> selectgoods();

    @Select("select * from goodsinfo where Goods_id=#{goodsid}")   @Results({@Result(column = "Goods_id",property = "goods_id"),
            @Result(column = "Goods_name",property = "goods_name"),
            @Result(column = "Goods_price",property = "goods_price"),
            @Result(column = "goods_type",property = "goods_type"),
            @Result(column = "goods_state",property = "goods_state"),
            @Result(column = "Goods_stock",property = "goods_stock"),
            @Result(column = "goods_sell",property = "goods_sell"),
            @Result(column = "goods_brand",property = "goods_brand"),
            @Result(column = "goods_img",property = "goods_img"),
            @Result(column = "goods_discrible",property = "goods_discrible"),
    })
    List<Goods> selectgoodsbyid(int goodsid);

    @Select("select * from goodsinfo where Goods_name like '%${goodsname}%'")   @Results({@Result(column = "Goods_id",property = "goods_id"),
            @Result(column = "Goods_name",property = "goods_name"),
            @Result(column = "Goods_price",property = "goods_price"),
            @Result(column = "goods_type",property = "goods_type"),
            @Result(column = "goods_state",property = "goods_state"),
            @Result(column = "Goods_stock",property = "goods_stock"),
            @Result(column = "goods_sell",property = "goods_sell"),
            @Result(column = "goods_brand",property = "goods_brand"),
            @Result(column = "goods_img",property = "goods_img"),
            @Result(column = "goods_discrible",property = "goods_discrible"),
    })
    List<Goods> selectgoodsbyname(String goodsname);

    @Select("select max(Goods_id) from goodsinfo ")   @Results({@Result(column = "max(Goods_id)",property = "goods_id"),
    })
    Goods selectmaxid();

    @Insert("insert into goodsinfo values(#{goods_id},#{goods_name},#{goods_price},#{goods_type},#{goods_state},#{goods_stock},#{goods_sell},#{goods_brand},#{goods_img},#{goods_discrible})")
    int addgood(int goods_id,String goods_name, float goods_price,int goods_type, int goods_state,int goods_stock,int goods_sell,String goods_brand,String goods_img,String goods_discrible);

    @Update("update goodsinfo set goods_name=#{goods_name},goods_price=#{goods_price},goods_type=#{goods_type},goods_state=#{goods_state},goods_stock=#{goods_stock},goods_sell=#{goods_sell},goods_brand=#{goods_brand},goods_img=#{goods_img},goods_discrible=#{goods_discrible} where goods_id=#{goods_id}")
    int updategoods(String goods_name, float goods_price,int goods_type, int goods_state,int goods_stock,int goods_sell,String goods_brand,String goods_img,String goods_discrible,int goods_id);

    @Delete("delete from goodsinfo where goods_id=#{goodsid}")
    int deletegoods(int goodsid);

    @Update("update goodsinfo set goods_state=#{goods_state} where Goods_id=#{goods_id}")
    int updatestate(int goods_state,int goods_id);


}