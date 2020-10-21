package s81project.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import s81project.Model.pojo.Brand;
import s81project.Service.BrandService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class BrandController {
    @Autowired
    BrandService brandService;

    @Autowired
    private HttpServletRequest request;

    @RequestMapping("/turnaddbrand")
    public String turnaddbrand(Model  model,
                               HttpSession session){
        model.addAttribute("username",session.getAttribute("username")) ;
        model.addAttribute("img",session.getAttribute("img"));
        model.addAttribute("res",0);
        return "add_brand";
    }

    @RequestMapping("/addbrand")
    public String addbrand(Model  model,
                           HttpSession session,
                           @RequestParam("userimg") MultipartFile file,
                           @ModelAttribute() Brand brand){
        String filename = file.getOriginalFilename();
        brandService.upLoadFile(file);
        int res=brandService.addbrand(brand.getGoods_brand(),brand.getGoods_brand_describe(),brand.getGoods_brand_states(),filename);
        if (res!=0){
           model.addAttribute("res",1);
       }else {
           model.addAttribute("res",0);
       }
        model.addAttribute("username",session.getAttribute("username")) ;
        model.addAttribute("img",session.getAttribute("img"));
        return "add_brand";
    }

    @RequestMapping("/turnbrandinfo")
    public  String turnbrandinfo(Model model){
        List<Brand> list=brandService.selectbrand();
        model.addAttribute("list", list);
        return "brandinfo";
    }

    @RequestMapping("/turnupdatebrand")
    public String turnupdatebrand(@RequestParam("goods_brand") String goods_brand, Model model,
            HttpSession session) {
        Brand brand=brandService.selectbrandbyname(goods_brand);

        model.addAttribute("brand", brand);

        model.addAttribute("username", session.getAttribute("username"));
        model.addAttribute("img",session.getAttribute("img"));
        model.addAttribute("res", 0);
        return "update_brand";
    }

    @RequestMapping("/updatebrand")
    public String updatebrand(@ModelAttribute Brand brand, Model model,@RequestParam("userimg") MultipartFile file ) {
        if (file.getOriginalFilename().trim().equals("")==false){
            String filename =file.getOriginalFilename();
            brand.setGoods_brand_img(filename);
            brandService.upLoadFile(file);
        }else {
            brand.setGoods_brand_img(brandService.selectbrandbyname(brand.getGoods_brand()).getGoods_brand_img());
        }
        int a = brandService.updatebrand(brand);
        if (a != 0) {
            model.addAttribute("res", 1);
            System.out.println("成功");
        } else {
            model.addAttribute("res", 0);
            System.out.println("失败");

        }
        return turnbrandinfo(model);
    }

    @RequestMapping("/selectbrandbyname")
    @ResponseBody
    public List<Brand> selectbrandbyname(Model model,
                                         @RequestParam(value = "text2", required = false) String brandname,
                                         HttpSession session) {
        List<Brand> list=brandService.selectbrandlikename(brandname);
        model.addAttribute("username", session.getAttribute("username"));
        model.addAttribute("img",session.getAttribute("img"));
        //   return "update_goods";
        return list;
    }

}
