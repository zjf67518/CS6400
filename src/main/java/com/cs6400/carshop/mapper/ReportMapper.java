package com.cs6400.carshop.mapper;

import com.cs6400.carshop.bean.*;
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

    ArrayList<GrossCustomer> selectTop15Customer();

    Integer selectRepair(Long customer_id);

    Integer selectSale(Long customer_id);

    ArrayList<SaleDetail> selectSaleDetail(Long customer_id);

    ArrayList<String> selectVinByCustomerId(Long customer_id);
    //查找该客户未完成的维修
    ArrayList<RepairInfo> selectRepairInfoByCustomerNotDone(Long customer_id);
    //查找该客户完成的维修
    ArrayList<RepairInfo> selectRepairInfoByCustomerDone(Long customer_id);
}
