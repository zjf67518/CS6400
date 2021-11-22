package com.cs6400.carshop.mapper;

import com.cs6400.carshop.bean.Part;
import com.cs6400.carshop.bean.Repair;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface RepairMapper {
    ArrayList<Repair> searchRepairByVIN(String VIN);
    void insertRepair(Repair repair);
    void updateRepair(Repair repair);
    void completeRepair(Repair repair);

    void insertPart(Part part);
    void updatePart(Part part);

}
