package com.cs6400.carshop;

import com.cs6400.carshop.bean.Part;
import com.cs6400.carshop.bean.Repair;
import com.cs6400.carshop.bean.RepairInfo;
import com.cs6400.carshop.service.RepairService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@SpringBootTest
public class RepairTest {
    @Autowired
    private RepairService repairService;

    @Test
    public void testCanBeRepaired(){
        String VIN = "Car1";
        System.out.println(repairService.availableForRepair(VIN));
    }
    @Test
    public void testInsertRepair(){
        Repair repair = new Repair();
        repair.setVIN("Car1");
        repair.setCustomer_id(1L);
        repair.setOdometer_reading("10000");
        repair.setService_writer_user_name("store_owner");
        repairService.insertRepair(repair);
    }

    @Test
    public void testUpdateRepair() {
        Repair repair = new Repair();
        repair.setVIN("Car1");
        repair.setLabel_charge(BigDecimal.valueOf(12223));
        repairService.updateRepair(repair);
    }

    @Test
    public void testInsertPart() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String stringTypeDate = "2021-11-22 16:40:39";
        Date date = sdf.parse(stringTypeDate);
        Part part = new Part();
        part.setStart_date(date);
        part.setVIN("Car1");
        part.setQuantity(3);
        part.setVendor_name("BMW");
        part.setUnit_price(BigDecimal.valueOf(12.000));
        part.setPart_name("Hammer");
        repairService.insertPart(part);

    }

    @Test
    public void testUpdatePart() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String stringTypeDate = "2021-11-22 16:40:39";
        Date date = sdf.parse(stringTypeDate);
        Part part = new Part();
        part.setStart_date(date);
        part.setVIN("Car1");
        part.setVendor_name("Benz");
        part.setPart_name("Hammer");
        part.setQuantity(8);
//        part.setUnit_price(BigDecimal.valueOf(100.00));
        repairService.updatePart(part);

    }

    @Test
    public void testRepairInfos(){
        ArrayList<RepairInfo> list = repairService.searchRepairInfosByVIN("Car1");
        for(RepairInfo info: list){
            System.out.println(info);
        }
    }
}
