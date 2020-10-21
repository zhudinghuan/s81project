package s81project.Service.Serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import s81project.Model.Mapper.BrandMapper;
import s81project.Model.pojo.Brand;
import s81project.Service.BrandService;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class BrandServiceimpl implements BrandService {
@Autowired
    BrandMapper brandMapper;
    @Override
    public List<Brand> selectbrand() {
        List<Brand> list=brandMapper.selectbrand();
        return list;
    }

    @Override
    public int addbrand(String goods_brand, String goods_brand_describe, String goods_brand_states, String goods_brand_img) {
        int res=brandMapper.addbrand(goods_brand,goods_brand_describe,goods_brand_states,goods_brand_img);
        return res;
    }

    @Override
    public void upLoadFile(MultipartFile file) {
        String fileName = file.getOriginalFilename();  //获取上传文件的名字
        //  System.out.println( this.getClass().getResource("/templates/goodsimg/"));

        String filePath="C:/Users/16560/IdeaProjects/s81springboot/s81project/src/main/resources/templates/brandimg/";

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
    public int updatebrand(Brand brand) {
        int res=brandMapper.updatebrand(brand);
        return res;
    }

    @Override
    public Brand selectbrandbyname(String goods_brand) {
        Brand brand=brandMapper.selectbrandbyname(goods_brand).get(0);
        return brand;
    }

    @Override
    public List<Brand> selectbrandlikename(String goods_brand) {
        List<Brand> list  =brandMapper.selectbrandlikename(goods_brand);
        return list;
    }
}
