package com.example.zhuzhourailway.Service.ServiceImpl;

import com.example.zhuzhourailway.Model.Dao.TrainMapper;
import com.example.zhuzhourailway.Model.Pojo.Train;
import com.example.zhuzhourailway.Service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class TrainServiceImpl implements TrainService {

    @Autowired
    TrainMapper trainMapper;
    @Override
    public List<Train> selecttrain(String endstation, String day) {
        List<Train> trains= trainMapper.selecttrain(endstation,day);
        return trains;
    }

    @Override
    public long difhour(String starttime, String endtime, int overday) {
        DateFormat df = new SimpleDateFormat("HH:mm:ss");
        long hours = 0;
        try
        {
            Date d1 = df.parse(endtime);
            Date d2 = df.parse(starttime);
            long diff = d1.getTime()+overday* (1000 * 60 * 60 * 24)- d2.getTime();//这样得到的差值是毫秒级别
            long days = diff / (1000 * 60 * 60 * 24);

           hours = (diff-days*(1000 * 60 * 60 * 24))/(1000* 60 * 60);
//            System.out.println(""+days+"天"+hours+"小时"+minutes+"分");
        }catch (Exception e)
        {
        }
     
        return hours;
    }

    @Override
    public long difday(String starttime, String endtime, int overday) {
        DateFormat df = new SimpleDateFormat("HH:mm:ss");
        long days = 0;
        try
        {
            Date d1 = df.parse(endtime);
            Date d2 = df.parse(starttime);
            long diff = d1.getTime()+overday* (1000 * 60 * 60 * 24)- d2.getTime();//这样得到的差值是毫秒级别
             days = diff / (1000 * 60 * 60 * 24);
        }catch (Exception e)
        {
        }
        return days;
    }

    @Override
    public long difminutes(String starttime, String endtime, int overday) {
        DateFormat df = new SimpleDateFormat("HH:mm:ss");
        long minutes = 0;
        try
        {
            Date d1 = df.parse(endtime);
            Date d2 = df.parse(starttime);
            long diff = d1.getTime()+overday* (1000 * 60 * 60 * 24)- d2.getTime();//这样得到的差值是毫秒级别
            long days = diff / (1000 * 60 * 60 * 24);
            long hours = (diff-days*(1000 * 60 * 60 * 24))/(1000* 60 * 60);
            minutes = (diff-days*(1000 * 60 * 60 * 24)-hours*(1000* 60 * 60))/(1000* 60);
        }catch (Exception e)
        {
        }
        return minutes;
    }

    @Override
    public Train selecttrainbyid(int id) {
     return    trainMapper.selecttrainbyid(id);
    }

    @Override
    public int updateTrain(int leftcarriage, int t_id) {
       return trainMapper.updateTrain(leftcarriage,t_id);
    }


}
