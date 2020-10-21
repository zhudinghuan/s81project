package s81project.Model.Mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import s81project.Model.pojo.Brand;

import java.util.List;

@Mapper
@Repository
public interface BrandMapper  {
    @Select("select * from g_b_table")
    @Results({@Result(column = "Goods_brand",property = "goods_brand"),
            @Result(column = "Goods_brand_describe",property = "goods_brand_describe"),
            @Result(column = "Goods_brand_states",property = "goods_brand_states"),
            @Result(column = "Goods_brand_img",property = "goods_brand_img")
    })
    List<Brand> selectbrand();

    @Select("select * from g_b_table where Goods_brand=#{goods_brand} ")
    @Results({@Result(column = "Goods_brand",property = "goods_brand"),
            @Result(column = "Goods_brand_describe",property = "goods_brand_describe"),
            @Result(column = "Goods_brand_states",property = "goods_brand_states"),
            @Result(column = "Goods_brand_img",property = "goods_brand_img")
    })
    List<Brand> selectbrandbyname(String goods_brand);


    @Select("select * from g_b_table where Goods_brand like '%${goods_brand}%'")
    @Results({@Result(column = "Goods_brand",property = "goods_brand"),
            @Result(column = "Goods_brand_describe",property = "goods_brand_describe"),
            @Result(column = "Goods_brand_states",property = "goods_brand_states"),
            @Result(column = "Goods_brand_img",property = "goods_brand_img")
    })
    List<Brand> selectbrandlikename(String goods_brand);

    @Update("update g_b_table set Goods_brand=#{goods_brand} ,Goods_brand_describe=#{goods_brand_describe},Goods_brand_states=#{goods_brand_states},Goods_brand_img=#{goods_brand_img} where Goods_brand=#{goods_brand} ")
    int updatebrand(Brand brand);

    @Insert("insert into g_b_table values(#{goods_brand},#{goods_brand_describe},#{goods_brand_states},#{goods_brand_img})")
    int addbrand(String goods_brand,String goods_brand_describe,String goods_brand_states,String goods_brand_img);
}
