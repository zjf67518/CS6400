package com.cs6400.carshop.mapper;

import com.cs6400.carshop.bean.Part;
import com.cs6400.carshop.bean.PartStatistic;
import com.cs6400.carshop.bean.Repair;
import com.cs6400.carshop.bean.RepairInfo;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;
import java.util.ArrayList;

@Mapper
public interface RepairMapper {
    ArrayList<Repair> searchRepairByVIN(String VIN);
    void insertRepair(Repair repair);
    void updateRepair(Repair repair);
    void completeRepair(Repair repair);

    void insertPart(Part part);
    void updatePart(Part part);

    Part searchPartByPK(Part part);


    //获取VIN对应的已经修完的记录
    ArrayList<RepairInfo> searchRepairInfosByVIN(String VIN);

    BigDecimal searchPartFee(RepairInfo repairInfo);


    void insertRepairTest(Repair repair);



}
