package s81project.Model.Mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import s81project.Model.pojo.User;

@Mapper
@Repository
public interface UserMapper {
    @Select("select * from t_user where username=#{username}")
    @Results({
            @Result(column = "username",property = "username"),
            @Result(column = "password",property = "password"),
            @Result(column = "phonenumber",property = "phonenumber"),
            @Result(column = "img",property = "img"),
            @Result(column = "userid",property = "userid"),
            @Result(column = "usergender",property = "usergender"),
    })
    User userlogin (String username);
    @Select("select * from t_user where userid=#{userid}")
    @Results({
            @Result(column = "username",property = "username"),
            @Result(column = "password",property = "password"),
            @Result(column = "usergender",property = "usergender"),
            @Result(column = "phonenumber",property = "phonenumber"),
            @Result(column = "userid",property = "userid"),
            @Result(column = "img",property = "img"),
    })
    User userselect (int userid);
    @Update("update t_user set phonenumber=#{phonenumber},usergender=#{usergender},img=#{img} where userid=#{userid}")
    int userupdate(String phonenumber,String usergender,int userid,String img);


    @Update("update t_user set password=#{password} where userid=#{userid}")
    int updatepwd(String password,int userid);
}