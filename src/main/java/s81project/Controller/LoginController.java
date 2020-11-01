package s81project.Controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.afs.model.v20180112.AuthenticateSigRequest;
import com.aliyuncs.afs.model.v20180112.AuthenticateSigResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import s81project.Model.pojo.User;
import s81project.Service.Userservice;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    Userservice userservice;
    int res=0;

    @RequestMapping("/check")
    public String checkres(HttpServletRequest req,Model model) throws ClientException {
        String regionid = "cn-hangzhou";
        // Create a new IClientProfile instance
                //添加阿里云密钥getProfile(regionid,accesskey,accesskey);
        IAcsClient client = new DefaultAcsClient(profile);

       DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou","afs", "afs.aliyuncs.com");
        String sessionId=req.getParameter("csessionid");
        String token=req.getParameter("token");
        AuthenticateSigRequest request = new AuthenticateSigRequest();
        request.setSessionId(sessionId);// 会话ID。必填参数，从前端获取，不可更改。
        request.setSig(req.getParameter("sig"));// 签名串。必填参数，从前端获取，不可更改。
        request.setToken(token);// 请求唯一标识。必填参数，从前端获取，不可更改。
        request.setScene("nc_login");// 场景标识。必填参数，从前端获取，不可更改。
        request.setAppKey("FFFF0N0000000000963C");// 应用类型标识。必填参数，后端填写。
        request.setRemoteIp("192.168.1.1");// 客户端IP。必填参数，后端填写。

        try {
            AuthenticateSigResponse response = client.getAcsResponse(request);
            if(response.getCode() == 100) {
                System.out.println("验签通过");
                model.addAttribute("message","");
                this.res=1;
            } else {
                System.out.println("验签失败");
            }
            // TODO
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index";
    }

    @RequestMapping("/login")
    public String login(@ModelAttribute User user,
                        Model model,

                        HttpSession session) throws ClientException {

        if ("".equals(user.getPassword())||"".equals(user.getUsername())){
            model.addAttribute("message","登录信息不为空");

            return "index";
        }
        User Dbuser=userservice.userlogin(user.getUsername());
        if (Dbuser==null){
            model.addAttribute("message","");
            return "index";
        }
        if (res==0){
            model.addAttribute("message","请完成验证");
            return "index";
        }

        if (user.getUsername().equals(Dbuser.getUsername())&&user.getPassword().equals(Dbuser.getPassword())){


            session.setAttribute("userid",Dbuser.getUserid());
            session.setAttribute("pwd",Dbuser.getPassword());
            session.setAttribute("username",Dbuser.getUsername());
            session.setAttribute("img",Dbuser.getImg());
            model.addAttribute("username",session.getAttribute("username")) ;
            return "shouye";
        }else {
            model.addAttribute("message","密码错误+++");
            return "index";
        }
    }
}
