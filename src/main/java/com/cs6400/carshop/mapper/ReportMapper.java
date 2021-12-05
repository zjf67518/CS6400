package com.cs6400.carshop.mapper;

import com.cs6400.carshop.bean.Color;
import com.cs6400.carshop.bean.MonthReport;
import com.cs6400.carshop.bean.MonthSalesperson;
import com.cs6400.carshop.bean.PartStatistic;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
@Mapper
public interface ReportMapper {

    ArrayList<String> selectAllTransactionVINAllTime();

    ArrayList<String> selectAllTransactionVINByMonth();

    ArrayList<String> selectAllTransactionVINByYear();


    ArrayList<PartStatistic> selectPartStatistic();

    Double selectInventoryDayByVehicleType(int vehicle_type);

    ArrayList<MonthReport> selectMonthlyReport();

    MonthSalesperson selectTopSalesperson(String date);
}
