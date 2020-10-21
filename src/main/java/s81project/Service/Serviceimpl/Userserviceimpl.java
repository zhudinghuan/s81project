package s81project.Service.Serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;
import s81project.Model.Mapper.UserMapper;
import s81project.Model.pojo.User;
import s81project.Service.Userservice;

import java.io.File;
import java.io.IOException;

@Service
public class Userserviceimpl implements Userservice {
    @Autowired
    UserMapper userMapper;
    @Override
    public User userlogin(String username) {
        User user=userMapper.userlogin(username);
        return user;
    }

    @Override
    public int userupdate( String phonenumber, String usergender,int userid,String img) {
        int aa=userMapper.userupdate(phonenumber,usergender,userid,img);
        return aa;
    }

    @Override
    public User userselect(int userid) {
        User user=userMapper.userselect(userid);
        return user;
    }

    @Override
    public int updatepassword(String password, int userid) {
        int aa=userMapper.updatepwd(password,userid);
        return aa;
    }

    @Override
    public void upLoadFile(MultipartFile file) {
        String fileName = file.getOriginalFilename();  //获取上传文件的名字
        //  System.out.println( this.getClass().getResource("/templates/goodsimg/"));

        String filePath="C:/Users/16560/IdeaProjects/s81springboot/s81project/src/main/resources/templates/userimg/";
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
}
