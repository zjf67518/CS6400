package com.cs6400.carshop.utils.Enum;

public enum VehicleType {
    Car(1), Convertible(2), Truck(3), Van(4), SUV(5);

    private int type;

    public int getType() {
        return type;
    }

    VehicleType(int type) {
        this.type = type;
    }

    public static String TransferTypeToName(int type) {
        switch (type) {
            case 1:
                return "Car";
            case 2:
                return "Convertible";
            case 3:
                return "Truck";
            case 4:
                return "Van";
            case 5:
                return "SUV";
            default:
                return "null";
        }
    }

}
