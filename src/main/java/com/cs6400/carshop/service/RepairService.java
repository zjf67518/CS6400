package com.cs6400.carshop.service;

import com.cs6400.carshop.bean.Customer;
import com.cs6400.carshop.bean.Part;
import com.cs6400.carshop.bean.Repair;
import com.cs6400.carshop.bean.RepairInfo;
import com.cs6400.carshop.mapper.RepairMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RepairService {
    @Autowired
    private RepairMapper repairMapper;
    @Autowired
    private CustomerService customerService;
    /**
     * 判断卖掉的车辆是否可以维修 是：新建维修 否：说明车正在维修, 只能修改维修
     * @param VIN
     * @return
     */
    public Repair availableForRepair(String VIN){
        ArrayList<Repair> list = repairMapper.searchRepairByVIN(VIN);
        for(Repair repair:list){
            if (repair.getComplete_date() == null){
                return repair;
            }
        }
        return null;

    }
    //新建维修
    public void insertRepair(Repair repair){
        repairMapper.insertRepair(repair);
    }

    //修改维修 主要是修改label_charge
    public void updateRepair(Repair repair){
        repairMapper.updateRepair(repair);
    }

    public void completeRepair(Repair repair){
        repairMapper.completeRepair(repair);
    }
    //新建零件
    public void insertPart(Part part){
        repairMapper.insertPart(part);
    }
    //修改零件
    public void updatePart(Part part){
        repairMapper.updatePart(part);
    }

    // input: Part 判断四元组是否存在 不存在就修改 存在就更新
    public void insertOrUpdatePart(Part part){
        Part originPart = repairMapper.searchPartByPK(part);
        if(originPart == null){
            repairMapper.insertPart(part);
        }else{
            repairMapper.updatePart(part);
        }
    }



    //根据VIN，搜索车辆的已完成维修的信息
    public ArrayList<RepairInfo> searchRepairInfosByVIN(String VIN){
        ArrayList<RepairInfo> repairInfos = repairMapper.searchRepairInfosByVIN(VIN);
        for(RepairInfo info: repairInfos){
            Long customer_id = info.getCustomer_id();
            Customer customer = customerService.searchCustomerById(customer_id);
            info.setPhone_number(customer.getPhone_number());
            info.setEmail(customer.getEmail());
            info.setAddress(customer.getAddress());
            info.setFirst_name(customer.getFirst_name());
            info.setLast_name(customer.getLast_name());
            info.setBusiness_name(customer.getBusiness_name());
            info.setContact(customer.getContact());
            info.setTitle(customer.getTitle());
            info.setPart_cost(repairMapper.searchPartFee(info));
            info.setTotal_cost(info.getLabel_charge().add(info.getPart_cost()));
        }
        return repairInfos;
    }

}
