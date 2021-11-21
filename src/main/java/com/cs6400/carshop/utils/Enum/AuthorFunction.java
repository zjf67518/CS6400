package com.cs6400.carshop.utils.Enum;

public enum AuthorFunction {
    AddCustomer(1), findCustomer(2), AddVehicle(4), SellVehicle(8),
    RepairVehicle(16), VehicleNumber(32), VehicleDetail(64), PirceFree(128);

    private int type;

    private AuthorFunction(int type) {

    }
}
