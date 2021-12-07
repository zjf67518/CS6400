package com.cs6400.carshop;

import com.cs6400.carshop.bean.*;
import com.cs6400.carshop.mapper.CustomerMapper;
import com.cs6400.carshop.mapper.RepairMapper;
import com.cs6400.carshop.mapper.TransactionMapper;
import com.cs6400.carshop.mapper.VehicleMapper;
import com.cs6400.carshop.service.CustomerService;
import com.cs6400.carshop.service.RepairService;
import com.univocity.parsers.tsv.TsvParser;
import com.univocity.parsers.tsv.TsvParserSettings;
import io.lettuce.core.ScriptOutputType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootTest

//插入官方数据
public class PartTest {
    @Autowired
    private RepairMapper repairMapper;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private VehicleMapper vehicleMapper;

    @Autowired
    private TransactionMapper transactionMapper;
    @Test
    public void testForInsert() throws FileNotFoundException, UnsupportedEncodingException, ParseException {



        TsvParserSettings settings = new TsvParserSettings();
        settings.getFormat().setLineSeparator("/t");
        TsvParser parser = new TsvParser(settings);

        FileReader file = new FileReader("C:/Users/woshihuahua/Desktop/6400/parts.tsv");
        List<String[]> allRows = parser.parseAll(file);
        for (int i = 1; i < allRows.size(); i++){
            List<String> part = Arrays.asList(allRows.get(i));
            //System.out.println(part);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            String stringTypeDate = part.get(1);
            Date date = sdf.parse(stringTypeDate);
            Part part1 = new Part();
            part1.setVIN(part.get(0));
            part1.setStart_date(date);
            part1.setPart_name(part.get(2));
            part1.setVendor_name(part.get(3));
            part1.setQuantity(Integer.valueOf(part.get(4)));
            part1.setUnit_price(BigDecimal.valueOf(Double.parseDouble(part.get(5))));

            repairMapper.insertPart(part1);
        }
    }

    @Test
    public void insertRegularUser() throws FileNotFoundException {
        TsvParserSettings settings = new TsvParserSettings();
        settings.getFormat().setLineSeparator("/t");
        TsvParser parser = new TsvParser(settings);

        FileReader file = new FileReader("C:/Users/woshihuahua/Desktop/6400/users.tsv");
        List<String[]> allRows = parser.parseAll(file);
        for (int i = 6; i < allRows.size(); i++){
            List<String> user = Arrays.asList(allRows.get(i));
            RegularUser regularUser = new RegularUser();
            regularUser.setUsername(user.get(0));
            regularUser.setPassword(user.get(1));
            regularUser.setFirst_name(user.get(2));
            regularUser.setLast_name(user.get(3));
            customerMapper.insertUser(regularUser);

            if(user.get(4) == "inventory_clerk"){
                customerMapper.insertInventoryClerk(regularUser.getUsername());
            } else if(user.get(4) == "sales_person"){
                customerMapper.insertSalePerson(regularUser.getUsername());
            }else if(user.get(4) == "manager"){
                customerMapper.insertManager(regularUser.getUsername());
            }else{
                customerMapper.insertWriter(regularUser.getUsername());
            }
        }



    }

    @Test
    public void insertIndividual() throws FileNotFoundException {
        TsvParserSettings settings = new TsvParserSettings();
        settings.getFormat().setLineSeparator("/t");
        TsvParser parser = new TsvParser(settings);

        FileReader file = new FileReader("C:/Users/woshihuahua/Desktop/6400/person.tsv");
        List<String[]> allRows = parser.parseAll(file);

        for(int i = 1; i < allRows.size(); i++) {
            List<String> individuals = Arrays.asList(allRows.get(i));

            Customer customer = new Customer();
            customer.setDriver_license(individuals.get(0));
            customer.setCustomer_id((long)i);
            customer.setFirst_name(individuals.get(1));
            customer.setLast_name(individuals.get(2));
            customer.setEmail(individuals.get(3));
            customer.setPhone_number(individuals.get(4));
            customer.setAddress(individuals.get(5));
            customerService.insertIndividual(customer);

        }
    }


    @Test
    public void insertRepair() throws FileNotFoundException, ParseException {
        TsvParserSettings settings = new TsvParserSettings();
        settings.getFormat().setLineSeparator("/t");
        TsvParser parser = new TsvParser(settings);

        FileReader file = new FileReader("C:/Users/woshihuahua/Desktop/6400/repairs.tsv");
        List<String[]> allRows = parser.parseAll(file);

        for(int i = 1; i < allRows.size(); i++) {
            List<String> repair = Arrays.asList(allRows.get(i));

            Repair repair1 = new Repair();
            repair1.setVIN(repair.get(0));
            String custom = repair.get(1);
            Long customer_id = customerMapper.searchCustomerIdByBusiness(custom);
            if(customer_id == null){
                customer_id = customerMapper.searchCustomerIdByIndividual(custom);
            }
            repair1.setCustomer_id(customer_id);
            repair1.setService_writer_user_name(repair.get(2));

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            String stringTypeDate = repair.get(3);
            Date date = sdf.parse(stringTypeDate);


            repair1.setStart_date(date);
            stringTypeDate = repair.get(4);
            date = sdf.parse(stringTypeDate);
            repair1.setComplete_date(date);

            repair1.setOdometer_reading(repair.get(5));
            repair1.setLabel_charge(BigDecimal.valueOf(Double.parseDouble(repair.get(6))));
            repair1.setDescription(repair.get(7));

            repairMapper.insertRepairTest(repair1);

        }
    }

    @Test
    public void insertVehicle() throws FileNotFoundException, ParseException {
        TsvParserSettings settings = new TsvParserSettings();
        settings.getFormat().setLineSeparator("/t");
        TsvParser parser = new TsvParser(settings);

        FileReader file = new FileReader("C:/Users/woshihuahua/Desktop/6400/vehicles.tsv");
        List<String[]> allRows = parser.parseAll(file);
        for(int i = 1; i < allRows.size(); i++) {
            List<String> vehicle = Arrays.asList(allRows.get(i));
            Vehicle vehicle1 = new Vehicle();
            vehicle1.setVIN(vehicle.get(0));
            vehicle1.setModel_year(Integer.valueOf(vehicle.get(1)));
            vehicle1.setManufacturer_id(vehicleMapper.selectManuIdByManuName(vehicle.get(2)));
            vehicle1.setModel_name(vehicle.get(3));
            vehicle1.setDescription(vehicle.get(5));
            vehicle1.setInvoice_price(BigDecimal.valueOf(Double.parseDouble(vehicle.get(6))));
            vehicle1.setInventory_clerk_user_name(vehicle.get(7));


            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            String stringTypeDate = vehicle.get(8);
            Date date = sdf.parse(stringTypeDate);
            vehicle1.setAdded_date(date);



            String vehicleType = vehicle.get(9);
            if(vehicleType.equals("SUV")){
                vehicle1.setCupholder_number(Integer.valueOf(vehicle.get(13)));
                vehicle1.setDrivetrain_type(vehicle.get(14));
                vehicle1.setVehicle_type(5);
                vehicleMapper.insertSUV(vehicle1);
            }else if(vehicleType.equals("Car")){
                vehicle1.setDoor_number(Integer.valueOf(vehicle.get(10)));
                vehicle1.setVehicle_type(1);
                vehicleMapper.insertCar(vehicle1);

            }else if(vehicleType.equals("Convertible")){
                vehicle1.setSeats_number(Integer.valueOf(vehicle.get(11)));
                vehicle1.setRoof_type(vehicle.get(12));
                vehicle1.setVehicle_type(2);
                vehicleMapper.insertConvertible(vehicle1);
            }else if(vehicleType.equals("Van")){
                vehicle1.setHas_back_door(Integer.valueOf(vehicle.get(18)));
                vehicle1.setVehicle_type(4);
                vehicleMapper.insertVan(vehicle1);
            }else if(vehicleType.equals("Truck")){
                vehicle1.setCargo_capacity((int)(Double.parseDouble(vehicle.get(17))));
                vehicle1.setCover_type(vehicle.get(16));
                vehicle1.setRear_axles_number(Integer.valueOf(vehicle.get(15)));
                vehicle1.setVehicle_type(3);
                vehicleMapper.insertTruck(vehicle1);
            }

            vehicleMapper.insertVehicleTest(vehicle1);


            String soldBy = vehicle.get(19);
            if(soldBy != null){

                Transaction transaction = new Transaction();
                transaction.setSales_person_user_name(soldBy);
                transaction.setVIN(vehicle.get(0));

                stringTypeDate = vehicle.get(20);
                date = sdf.parse(stringTypeDate);
                transaction.setSale_date(date);

                transaction.setSold_price(BigDecimal.valueOf(Double.parseDouble(vehicle.get(21))));
                String custom = vehicle.get(22);
                Long customer_id = customerMapper.searchCustomerIdByBusiness(custom);
                if(customer_id == null){
                    customer_id = customerMapper.searchCustomerIdByIndividual(custom);
                }
                transaction.setCustomer_id(customer_id);

                transactionMapper.insertTransactionTest(transaction);
            }




        }
    }

    @Test

    public void insertColor() throws FileNotFoundException {
        TsvParserSettings settings = new TsvParserSettings();
        settings.getFormat().setLineSeparator("/t");
        TsvParser parser = new TsvParser(settings);

        FileReader file = new FileReader("C:/Users/woshihuahua/Desktop/6400/vehicles.tsv");
        List<String[]> allRows = parser.parseAll(file);
        for(int i = 1; i < allRows.size(); i++) {
            List<String> vehicle = Arrays.asList(allRows.get(i));
            String VIN = vehicle.get(0);
            String Colors = vehicle.get(4);
            char[] cc = Colors.toCharArray();
            if(cc[0] != '"'){
                vehicleMapper.insertColor(VIN,Colors);
            }else{
                StringBuffer sb = new StringBuffer();
                for(int j = 1; j < cc.length-1;j++){

                    sb.append(cc[j]);
                }
                String[] colors = sb.toString().split(",");
                for(String cl:colors){
                    vehicleMapper.insertColor(VIN,cl);
                }
            }
        }
    }







}
