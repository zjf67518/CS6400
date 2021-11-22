package com.cs6400.carshop;

import com.cs6400.carshop.bean.Vehicle;
import com.cs6400.carshop.mapper.VehicleMapper;
import com.cs6400.carshop.service.VehicleService;
import com.cs6400.carshop.utils.converter.SearchInfoConverter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class VehicleTest {
    @Autowired
    private VehicleService vehicleService;
    @Test
    public void testForSale(){
        System.out.println(vehicleService.selectVehicleForSale());
    }

    @Test
    public void testForSearch(){
        SearchInfoConverter searchInfoConverter = new SearchInfoConverter();
        searchInfoConverter.setKeyword("Car1");
//        searchInfoConverter.setVehicle_type(1);
        List<Vehicle> list = vehicleService.searchVehicleUsedByCustomer(searchInfoConverter);
        for(Vehicle vehicle : list){
            System.out.println(vehicle);
        }


    }

    @Test
    public void testForDetail(){
        Vehicle vehicle = vehicleService.searchVehicleDetail("Car1");
        System.out.println(vehicle);
    }

    @Test
    public void testForInsertVehicle(){
        Vehicle vehicle = new Vehicle();
        vehicle.setVIN("SUV3");
        vehicle.setManufacturer_id(1);
        vehicle.setModel_name("model3");
        vehicle.setModel_year(1995);
        vehicle.setInvoice_price(BigDecimal.valueOf(199999));
        vehicle.setInventory_clerk_user_name("InventoryClerk1");
        vehicle.setVehicle_type(5);
        vehicle.setCupholder_number(2);
        vehicle.setDrivetrain_type("suv_type_2");
        vehicle.setColor("white,red,blue");
        vehicleService.addVehicle(vehicle);


    }

    @Test
    public void testForSearchVehicleForRepair(){
        String VIN = "SUV2";
        Vehicle vehicle = vehicleService.searchVehicleForRepair(VIN);
        System.out.println(vehicle);
    }

    @Test
    public void testInvoicePrice(){
        System.out.println(vehicleService.searchInvoicePrice("Car1"));
    }
}
