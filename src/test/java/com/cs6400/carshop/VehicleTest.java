package com.cs6400.carshop;

import com.cs6400.carshop.mapper.VehicleMapper;
import com.cs6400.carshop.service.VehicleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class VehicleTest {
    @Autowired
    private VehicleService vehicleService;
    @Test
    public void testForSale(){
        System.out.println(vehicleService.selectVehicleForSale());
    }
}
