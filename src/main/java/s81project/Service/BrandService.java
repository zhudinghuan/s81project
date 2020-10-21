package s81project.Service;

import org.springframework.web.multipart.MultipartFile;
import s81project.Model.pojo.Brand;

import java.util.List;

public interface BrandService {
    public List<Brand> selectbrand();

    public int addbrand(String goods_brand,String goods_brand_describe,String goods_brand_states,String goods_brand_img);

    public void upLoadFile(MultipartFile file);

    public int updatebrand(Brand brand);

  Brand selectbrandbyname(String goods_brand);

    List<Brand> selectbrandlikename(String goods_brand);

}
