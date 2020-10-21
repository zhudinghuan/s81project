package s81project.Service;

import org.springframework.web.multipart.MultipartFile;
import s81project.Model.pojo.User;

public interface Userservice {
    User userlogin(String username);
    int userupdate(String phonenumber,String usergender,int userid,String img);
    User userselect(int userid);
    int updatepassword(String password,int userid);
    public void upLoadFile(MultipartFile file);

}
