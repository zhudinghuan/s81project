package s81project.Service.Serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import s81project.Model.Mapper.Goodsmapper;
import s81project.Model.Mapper.UserMapper;
import s81project.Model.pojo.Goods;
import s81project.Service.GoodsService;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class GoodsServiceimpl implements GoodsService {
    @Autowired
    Goodsmapper goodsmapper;

    @Override
    public List<Goods> selectgoods() {
        List<Goods> list=goodsmapper.selectgoods();
        return list;
    }

    @Override
    public List<Goods> selectgoodsbyid(int goodsid) {
        List<Goods> list=goodsmapper.selectgoodsbyid(goodsid);
        return list;
    }

    @Override
    public List<Goods> selectgoodsbyname(String goodsname) {
        List<Goods> list=goodsmapper.selectgoodsbyname(goodsname);
        return list;
    }

    @Override
    public int selectmaxid() {
        int maxid=goodsmapper.selectmaxid().getGoods_id();

        return maxid;
    }

    @Override
    public int addgoods(int goods_id, String goods_name, float goods_price, int goods_type,int goods_state, int goods_stock, int goods_sell, String goods_brand, String goods_img, String goods_discrible) {
        int res=goodsmapper.addgood(goods_id,goods_name,goods_price,goods_type,goods_state,goods_stock,goods_sell,goods_brand,goods_img,goods_discrible);
        return  res;
    }

    @Override
    public int updategoods( String goods_name, float goods_price, int goods_type, int goods_state, int goods_stock, int goods_sell, String goods_brand, String goods_img, String goods_discrible,int goods_id) {
        int res=goodsmapper.updategoods(goods_name,goods_price,goods_type,goods_state,goods_stock,goods_sell,goods_brand,goods_img,goods_discrible,goods_id);
   return res;
    }

    @Override
    public void upLoadFile(MultipartFile file) {
        String fileName = file.getOriginalFilename();  //获取上传文件的名字
     //  System.out.println( this.getClass().getResource("/templates/goodsimg/"));

        String filePath="C:/Users/16560/IdeaProjects/s81springboot/s81project/src/main/resources/templates/goodsimg/";
        //判断文件夹是否存在,不存在则创建
        File file1=new File(filePath);
        if(!file1.exists()){
            file1.mkdirs();
        }
        //新文件的路径
        String newFilePath=filePath+fileName;

        //String newFilePath1=filePath1+fileName;
        try {
            //将传来的文件写入新建的文件
            file.transferTo(new File(newFilePath));
            //file.transferTo(new File(newFilePath1));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int deletegoods(int goodsid) {
        int res=goodsmapper.deletegoods(goodsid);
        return res;
    }

    @Override
    public int updatestate(int goods_state, int goods_id) {
        int res=goodsmapper.updatestate(goods_state,goods_id);
        return res;
    }

}
