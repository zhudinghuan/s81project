package s81project.Service;

import org.springframework.web.multipart.MultipartFile;
import s81project.Model.pojo.Goods;

import java.util.List;

public interface GoodsService {
    public List<Goods> selectgoods();

    public List<Goods> selectgoodsbyid(int goodsid);

    public List<Goods> selectgoodsbyname(String goodsname);

    public int selectmaxid();

    public int addgoods(int goods_id,String goods_name, float goods_price,int goods_type,int goods_state,int goods_stock,int goods_sell,String goods_brand,String goods_img,String goods_discrible);

    public int updategoods(String goods_name, float goods_price,int goods_type, int goods_state,int goods_stock,int goods_sell,String goods_brand,String goods_img,String goods_discrible,int goods_id);

    public void upLoadFile(MultipartFile file);

    public int deletegoods(int goodsid);

    public int updatestate(int goods_state,int goods_id);
}
