package com.example.zhuzhourailway.Model.Dao;

import com.example.zhuzhourailway.Model.Pojo.Train;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@Mapper
public interface TrainMapper {
    List<Train> selecttrain(String endstation, String day);
}
