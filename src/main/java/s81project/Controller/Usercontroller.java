package s81project.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import s81project.Model.pojo.User;
import s81project.Service.Userservice;


import javax.servlet.http.HttpSession;

@Controller
public class Usercontroller {
    @Autowired
    Userservice userservice;
//    User sp= spuser;
    @RequestMapping("/shouye")
    public String shouye(Model model,HttpSession session){
        model.addAttribute("username",session.getAttribute("username")) ;
        model.addAttribute("img",session.getAttribute("img"));
        return "shouye";

    }

    @RequestMapping("/updateuser")
    public String update(@ModelAttribute User user, Model  model, HttpSession session,
                         @RequestParam("userimg") MultipartFile file){
        String filename="";

        if (file.getOriginalFilename().trim().equals("")==false){

            filename =file.getOriginalFilename();
            System.out.println(filename);

            user.setImg(filename);
            userservice.upLoadFile(file);
        }else {
            filename=userservice.userselect(Integer.parseInt(session.getAttribute("userid").toString())).getImg();
        }
        int userid= (int) session.getAttribute("userid");

      int aa = userservice.userupdate(user.getPhonenumber(),user.getUsergender(),userid,filename);
        session.setAttribute("img",filename);
      if(aa!=0){
          model.addAttribute("result",aa);
          User user1=userservice.userselect(userid);
          model.addAttribute("user1",user1);
      }else {
          model.addAttribute("result",0);
      }
      return "lyear_pages_profile";
    }

    @RequestMapping("/select")
    public String selectinfo( Model model,HttpSession session){
//        System.out.println("dfjadf");
        int userid= (int) session.getAttribute("userid");
        User user1=userservice.userselect(userid);
        model.addAttribute("username",session.getAttribute("username")) ;
//        System.out.println(user1.getUsername());
        model.addAttribute("img",session.getAttribute("img"));

        model.addAttribute("user1",user1);
        model.addAttribute("result",0);
        return "lyear_pages_profile";
    }

@RequestMapping("/updatepassword")
    public String updatepwd(@RequestParam("newpwd") String newpwd,
                            HttpSession session,
                            Model model){
        int aa=userservice.updatepassword(newpwd,(int)session.getAttribute("userid"));
        model.addAttribute("pwd",session.getAttribute("pwd"));
        if (aa!=0) {
            model.addAttribute("result", 1);
            model.addAttribute("username",session.getAttribute("username")) ;
            model.addAttribute("img",session.getAttribute("img"));
        }else {
            model.addAttribute("result", 0);
        }
    return "lyear_pages_edit_pwd";
}
@RequestMapping("/selectpwd")
    public String selectpwd(HttpSession session,Model model){
           User user=userservice.userselect((int)session.getAttribute("userid"));
    model.addAttribute("username",session.getAttribute("username")) ;
    model.addAttribute("img",session.getAttribute("img"));
    String pwd=user.getPassword();

    model.addAttribute("pwd",pwd);



    return "lyear_pages_edit_pwd";

}
    @RequestMapping("/selectoldpwd")
    public String selectoldpwd(HttpSession session,Model model){
        User user=userservice.userselect((int)session.getAttribute("userid"));
        String pwd=user.getPassword();
        model.addAttribute("pwd",pwd);
        model.addAttribute("result",0);
        model.addAttribute("username",session.getAttribute("username")) ;
        model.addAttribute("img",session.getAttribute("img"));

        return "lyear_pages_edit_pwd";
    }

}
