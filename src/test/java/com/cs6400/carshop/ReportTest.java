package com.cs6400.carshop;

import com.cs6400.carshop.mapper.ReportMapper;
import com.cs6400.carshop.service.ReportService;
import io.lettuce.core.ScriptOutputType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class ReportTest {

    @Autowired
    private ReportMapper reportMapper;

    @Autowired
    private ReportService reportService;
    @Test
    public void selectVIN(){
        ArrayList<String> list = reportMapper.selectAllTransactionVINAllTime();
        ArrayList<String> list1 = reportMapper.selectAllTransactionVINByMonth();
        ArrayList<String> list2 = reportMapper.selectAllTransactionVINByYear();
        System.out.println(list);
        System.out.println(list1);
        System.out.println(list2);
    }
    @Test
    public void testColorReport(){
        Map<String,Integer> report = reportService.reportByColor(0);
        System.out.println(report);
        Map<String,Integer> report1 = reportService.reportByColor(1);
        System.out.println(report1);
        Map<String,Integer> report2 = reportService.reportByColor(2);
        System.out.println(report2);

    }

    @Test
    public void testVehicleTypeReport(){
        Map<String,Integer> report = reportService.reportByVehicleType(0);
        System.out.println(report);
        Map<String,Integer> report1 = reportService.reportByVehicleType(1);
        System.out.println(report1);
        Map<String,Integer> report2 = reportService.reportByVehicleType(2);
        System.out.println(report2);
    }

    @Test
    public void testManuReport(){
        Map<String,Integer> report = reportService.reportByManufacturer(0);
        System.out.println(report);
        Map<String,Integer> report1 = reportService.reportByManufacturer(1);
        System.out.println(report1);
        Map<String,Integer> report2 = reportService.reportByManufacturer(2);
        System.out.println(report2);
    }

    @Test
    public void testPartReport(){
        System.out.println(reportService.reportByPart());
    }

    @Test
    public void testBelowCost(){
        System.out.println(reportService.reportBelowCostSales());
    }

    @Test
    public void testInventoryDays(){
        System.out.println(reportService.reportInventoryDays());
    }
}
