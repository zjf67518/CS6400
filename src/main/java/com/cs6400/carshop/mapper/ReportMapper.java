package com.cs6400.carshop.mapper;

import com.cs6400.carshop.bean.Color;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
@Mapper
public interface ReportMapper {

    ArrayList<String> selectAllTransactionVINAllTime();
    ArrayList<String> selectAllTransactionVINByMonth();
    ArrayList<String> selectAllTransactionVINByYear();

}
