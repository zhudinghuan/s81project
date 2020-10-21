package s81project.Controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import s81project.Model.Mapper.Goodsmapper;
import s81project.Model.pojo.Brand;
import s81project.Model.pojo.Goods;
import s81project.Model.pojo.User;
import s81project.Service.BrandService;
import s81project.Service.GoodsService;
import s81project.Service.Serviceimpl.GoodsServiceimpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Controller
public class GoodsController {
    @Autowired
    GoodsService goodsService;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    BrandService brandService;

    @RequestMapping("/selectgoods")
    public String selectgoods(Model model, HttpSession session) {
        PageHelper.startPage(1, 5);
        List<Goods> list = goodsService.selectgoods();
        model.addAttribute("username", session.getAttribute("username"));
        model.addAttribute("img", session.getAttribute("img"));
        for (Goods goods : list) {
            if (goods.getGoods_state() == 1) {
                goods.setState("在售");
            } else {
                goods.setState("下架");
            }
        }
        model.addAttribute("PrePage",1);
        model.addAttribute("list", list);
        model.addAttribute("page",1);
        return "update_goods";
    }


    @RequestMapping("/selectgoodsbyid")
    @ResponseBody
    public List<Goods> selectgoodsbyid(Model model,
                                       @RequestParam(value = "text2", required = false) String goodsid,
                                       HttpSession session) {
        List<Goods> list=list = new LinkedList();
        int goodsid1 = 0;
        try {
            goodsid1 = Integer.parseInt(goodsid);
        } catch (NumberFormatException e) {
            System.out.println("输入错误");
        }
        ValueOperations valueOperations=redisTemplate.opsForValue();
//        valueOperations.set("1","zhu");
        boolean haskey=redisTemplate.hasKey(goodsid1+"");
//        System.out.println("有吗："+haskey);
        if(haskey){
            Goods value=(Goods) valueOperations.get(goodsid1+"");
//            System.out.println(value.getGoods_id());
            list.add(value);
        }
        else {


//            System.out.println("调用了数据库");
            list = goodsService.selectgoodsbyid(goodsid1);
            if (!list.isEmpty()){
                valueOperations.set(list.get(0).getGoods_id()+"",list.get(0),20, TimeUnit.SECONDS);


//                System.out.println("加了之后有吗"+redisTemplate.hasKey(list.get(0).getGoods_id()+"")+list.get(0).getGoods_id());
            }
        }

        for (Goods goods : list) {
            if (goods.getGoods_state() == 1) {
                goods.setState("在售");
            } else {
                goods.setState("下架");
            }
        }
        model.addAttribute("username", session.getAttribute("username"));
        model.addAttribute("img", session.getAttribute("img"));
        //   return "update_goods";
        return list;
    }

    @RequestMapping("/selectgoodsbyname")
    @ResponseBody
    public List<Goods> selectgoodsbyname(Model model,
                                         @RequestParam(value = "text2", required = false) String goodsname,
                                         HttpSession session) {

        List<Goods> list = goodsService.selectgoodsbyname(goodsname);
        for (Goods goods : list) {
            if (goods.getGoods_state() == 1) {
                goods.setState("在售");
            } else {
                goods.setState("下架");
            }
        }
        model.addAttribute("username", session.getAttribute("username"));
        model.addAttribute("img", session.getAttribute("img"));

        //   return "update_goods";
        return list;
    }
@RequestMapping("/selectpage2")
    public String findAllUser(@RequestParam("page") int pageNum,@RequestParam("size") int pageSize
,Model model, HttpSession session) {
        //将参数传给这个方法就可以实现物理分页了，非常简单。
        //pageNum从第几条数据开始，pageSize每一页显示的数据
        PageHelper.startPage(pageNum, pageSize);
        List<Goods> goodsList= goodsService.selectgoods();
    PageInfo<Goods> pageinfo=new PageInfo<Goods>(goodsList,pageSize);
//    List<Goods> list = goodsService.selectgoods();
    model.addAttribute("username", session.getAttribute("username"));
    model.addAttribute("img", session.getAttribute("img"));
    for (Goods goods : goodsList) {
        if (goods.getGoods_state() == 1) {
            goods.setState("在售");
        } else {
            goods.setState("下架");
        }
    }
    model.addAttribute("list", goodsList);
    model.addAttribute("page",1);

    Map map=new HashMap();
    model.addAttribute("PrePage",pageinfo.getPrePage());
    model.addAttribute("NextPage",pageinfo.getNextPage());
//    map.put("size",pageSize);
//    System.out.println("上一页:"+pageinfo.getPrePage());
//    System.out.println("下一页:"+pageinfo.getNextPage());
    return "update_goods";
    }

    @RequestMapping("/turnaddgoods")
    public String turnaddgoods(Model model
            , HttpSession session) {
        int maxid = goodsService.selectmaxid() + 1;

        List<Brand> list = brandService.selectbrand();
        model.addAttribute("list", list);
        model.addAttribute("username", session.getAttribute("username"));
        model.addAttribute("img", session.getAttribute("img"));

        model.addAttribute("res", 0);
        model.addAttribute("maxid", maxid);
        return "add_goods";
    }

    @RequestMapping("/addgoods")
    public String addgoods(@ModelAttribute() Goods goods,
                           Model model, HttpSession session,
                           @RequestParam("img") MultipartFile file) {
        String filename = file.getOriginalFilename();
        goodsService.upLoadFile(file);
        int a = goodsService.addgoods(goods.getGoods_id(), goods.getGoods_name(), goods.getGoods_price(), 1, goods.getGoods_state(), goods.getGoods_stock(), goods.getGoods_sell(), goods.getGoods_brand(), filename, goods.getGoods_discrible());

        if (a != 0) {
            model.addAttribute("res", 1);
        } else {
            model.addAttribute("res", 0);
        }
        int maxid = goodsService.selectmaxid() + 1;

        List<Brand> list = brandService.selectbrand();
        model.addAttribute("list", list);
        model.addAttribute("username", session.getAttribute("username"));
        model.addAttribute("img", session.getAttribute("img"));
        model.addAttribute("maxid", maxid);
        return "add_goods";
    }

    @RequestMapping("/turnupdategoods")
    public String turnupdategoods(@RequestParam("goodsid") String goodsid, Model model
            , HttpSession session) {
        int goodsid1 = Integer.parseInt(goodsid);
        List<Goods> list = goodsService.selectgoodsbyid(goodsid1);
        List<Brand> list1 = brandService.selectbrand();
        Goods goods = list.get(0);
//        System.out.println(goods.getGoods_img());
        model.addAttribute("list", list1);
        model.addAttribute("goods", goods);
        model.addAttribute("username", session.getAttribute("username"));
        model.addAttribute("img", session.getAttribute("img"));
        model.addAttribute("res", 0);
        return "update_goodsinfo";
    }

    @RequestMapping("/updategoods")
    public String update(@ModelAttribute Goods goods, Model model, @RequestParam("img") MultipartFile file, HttpSession session) {
        String filename = "";
        if (file.getOriginalFilename().trim().equals("") == false) {
            filename = file.getOriginalFilename();
            goods.setGoods_img(filename);
            goodsService.upLoadFile(file);
        } else {
            filename = goodsService.selectgoodsbyid(goods.getGoods_id()).get(0).getGoods_img();
        }

        int a = goodsService.updategoods(goods.getGoods_name(), goods.getGoods_price(), 1, goods.getGoods_state(), goods.getGoods_stock(), goods.getGoods_sell(), goods.getGoods_brand(), filename, goods.getGoods_discrible(), goods.getGoods_id());

        if (a != 0) {
            model.addAttribute("res", 1);
        } else {
            model.addAttribute("res", 0);
        }
        return selectgoods(model, session);
    }

    @RequestMapping("deletegoods")
    public String deletegoods(@RequestParam("goodsid") String goodsid,
                              Model model, HttpSession session
    ) {
        int goodsid1 = Integer.parseInt(goodsid);
        int res = goodsService.deletegoods(goodsid1);
        return selectgoods(model, session);
    }

    @ResponseBody
    @RequestMapping("statetoundercarriage")
    public void statetoundercarriage(@RequestParam("goods_id") int goodsid) {
        goodsService.updatestate(0, goodsid);
    }

    @ResponseBody
    @RequestMapping("statetosell")
    public void statetosell(@RequestParam("goods_id") int goodsid) {
        goodsService.updatestate(1, goodsid);
    }

    @RequestMapping(value = "/ExcelOut", method = RequestMethod.GET)
    @ResponseBody
    public void ExcelOut(@RequestParam("text1") String text1,
                         @RequestParam(value = "tag" ,defaultValue = "0") int tag,
                         HttpServletResponse response) throws IOException {
//        System.out.println("hello");
//        Map<String, String> parameterMap = new HashMap<>();

        // 创建一个文件
        String path = new Date().getTime() + "_" + text1 + ".xlsx";//创建文件名，使用的是日期加’_’加用户名+后缀名，如果使用此处要改

        System.out.println(path);

//        parameterMap.put("userid", (String) session.getAttribute("userid"));
        List<Goods> lists = null;
        System.out.println(tag);
        if (tag == 1) {
            lists = goodsService.selectgoodsbyid(Integer.parseInt(text1));
        } else {
            lists = goodsService.selectgoodsbyname(text1);

        }
        String[] title = {"商品编号", "缩略图名称", "商品名称", "商品描述", "单价", "商品销量", "商品品牌", "状态"};//设置EXCEL的第一行的标题头（改）

        // 创建excel工作薄

        XSSFWorkbook workbook = new XSSFWorkbook();

        // 创建一个工作表sheet

        XSSFSheet sheet = workbook.createSheet();

        // 创建第一行

        XSSFRow row = sheet.createRow(0);

        XSSFCell cell = null;


        // 插入第一行数据 id 地区名称

        for (int i = 0; i < title.length; i++) {

            // 创建一行的一格

            cell = row.createCell(i);

            // 赋值

            cell.setCellValue(title[i]);

        }

        // 追加数据行数

        int j = 1;

        Goods goods = null;

        for (int i = 0; i < lists.size(); i++) {

            // 从集合中得到一个对象

            goods = lists.get(i);

            // 创建第2行


            XSSFRow nextrow = sheet.createRow(i + 1);

            // 创建第1列并赋值

            XSSFCell cessk = nextrow.createCell(0);

            cessk.setCellValue(goods.getGoods_id());//改

            cessk = nextrow.createCell(1);

            cessk.setCellValue(goods.getGoods_img());//改

            cessk = nextrow.createCell(2);
            cessk.setCellValue(goods.getGoods_name());//改

            cessk = nextrow.createCell(3);
            cessk.setCellValue(goods.getGoods_discrible());//改

            cessk = nextrow.createCell(4);
            cessk.setCellValue(goods.getGoods_price());//改

            cessk = nextrow.createCell(5);
            cessk.setCellValue(goods.getGoods_sell());//改

            cessk = nextrow.createCell(6);
            cessk.setCellValue(goods.getGoods_brand());//改

            cessk = nextrow.createCell(7);
            cessk.setCellValue(goods.getGoods_state());//改
            System.out.println(goods.getGoods_state());

            //可自己再按照需要添加函数，注意将cessk = nextrow.createCell(num);这其中的num修改就行，按照顺序加上1就行

            j++;

        }

        if (path.equals("")) {

            response.getWriter().write("失败，失败原因：参数为空！");

            return;

        }

        response.setContentType("application/vnd.ms-excel");

        response.setHeader("content-disposition", "attachment;fileName=" + URLEncoder.encode(path, "UTF-8"));

        OutputStream ouputStream;

        try {

            ouputStream = response.getOutputStream();

            workbook.write(ouputStream);

            ouputStream.flush();

            ouputStream.close();

        } catch (IOException e) {

        }

    }

}
