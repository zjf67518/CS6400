package com.cs6400.carshop.utils.Enum;

public enum AuthorFunction {
    AddCustomer(1), findCustomer(2), AddVehicle(4), SellVehicle(8),
    RepairVehicle(16), VehicleNumber(32), VehicleDetail(64), PriceFree(128);

    private int code;

    public int getCode() {
        return code;
    }

    AuthorFunction(int code) {
        this.code = code;
    }
}
