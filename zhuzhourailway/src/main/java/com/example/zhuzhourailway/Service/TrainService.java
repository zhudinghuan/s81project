package com.example.zhuzhourailway.Service;

import com.example.zhuzhourailway.Model.Pojo.Train;

import java.util.List;


public interface TrainService {
    List<Train> selecttrain(String endstation, String day);
    long difhour(String starttime,String endtime,int overday);
    long difday(String starttime,String endtime,int overday);
    long difminutes(String starttime,String endtime,int overday);
    Train selecttrainbyid(int id);
    int updateTrain(int carriage,int id);
}
