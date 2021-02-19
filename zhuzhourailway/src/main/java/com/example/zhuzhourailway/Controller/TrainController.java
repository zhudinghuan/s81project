package com.example.zhuzhourailway.Controller;

import com.example.zhuzhourailway.Model.Pojo.Train;
import com.example.zhuzhourailway.Service.TrainService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

@Controller
public class TrainController {

    @Autowired
    TrainService trainService;

    @RequestMapping("/selecttrain")
    public String selecttrain(@RequestParam(value = "endstation",required = false) String endstation ,
                              @RequestParam("day") String day,
                              Model model,
                              HttpSession session)  {
        //实现分页
        PageHelper.startPage(1,5);
        List<Train> trainList=trainService.selecttrain(endstation,day);
        if (trainList.isEmpty()==false){
            for ( Train t:
                    trainList) {
                long days=trainService.difday(t.getStarttime(),t.getEndtime(),t.getOverday());
                long hours=trainService.difhour(t.getStarttime(),t.getEndtime(),t.getOverday());
                long munites=trainService.difminutes(t.getStarttime(),t.getEndtime(),t.getOverday());
                t.setLishi(days+"天"+hours+"小时"+munites+"分钟");
            }
        }
        PageInfo<Train> pageInfo=new PageInfo<>(trainList,5);
         model.addAttribute("total",pageInfo.getTotal());
        model.addAttribute("endstation" ,endstation);
        model.addAttribute("day",day);
        model.addAttribute("user",session.getAttribute("user"));
        model.addAttribute("trainList",trainList);
        return "order-check";
    }
}
