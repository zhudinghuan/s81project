package com.example.zhuzhourailway.Controller;

import com.example.zhuzhourailway.Model.Dao.UserMapper;
import com.example.zhuzhourailway.Model.Pojo.User;
import com.example.zhuzhourailway.Service.UserService;
import com.example.zhuzhourailway.Utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService service;

    @RequestMapping("/s")
     public void  s(){

        List<User> userList=  service.selectall();
        for (User u:userList){
            System.out.println("名称"+u.getUsername());
        }
    }
    @RequestMapping("/ss")
    public void  ss(){
        SqlSession sqlSession= MybatisUtil.getSqlSession();
        UserMapper dao= sqlSession.getMapper(UserMapper.class);
        List<User> userList=  dao.selectall();
        for (User u:userList){
            System.out.println("名称"+u);
        }
    }

    @RequestMapping("/login.html")
    public String turnlogin(){
        return "login";
    }

    @RequestMapping("/index.html")
    public String  index(HttpSession session,
                         Model model){
            model.addAttribute("user",session.getAttribute("user"));
        return "index";
    }

    @RequestMapping(value = "/loginbyname",method = RequestMethod.POST)
    public String login(@RequestParam(value = "pwd", required = false) String pwd,
                        @RequestParam(value = "usernumber", required = false) String usernumber,
                        Model model,
                        HttpSession session){
        if ("".equals(pwd)||"".equals(usernumber)){
            model.addAttribute("message","登录信息不为空");
            return "login";
        }
        int usernumber1;
        try { usernumber1=Integer.parseInt(usernumber);
        } catch (Exception e) {
            model.addAttribute("message","号码格式有误");
            return "login";
        }
        User Duser=service.login(usernumber1);
        if (Duser.getUsernumber()==usernumber1&&Duser.getPwd().equals(pwd)){
                session.setAttribute("user",Duser);
                return "index";
            }else {
                model.addAttribute("message","密码错误");
                return "login";
            }
    }

    @RequestMapping("/adduser")
    public String adduser(@ModelAttribute User user,
                        Model model){
        service.adduser(user);
        model.addAttribute("target",1);
        return "register";
    }

    @RequestMapping("/main.html")
    public String main1(HttpSession session
            ,Model model){
        if (session.getAttribute("user")==null){
            model.addAttribute("target",1);
            model.addAttribute("user",session.getAttribute("user"));
            return "index";
        }
        return "main";

    }

}
