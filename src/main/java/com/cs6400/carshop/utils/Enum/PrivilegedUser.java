package com.cs6400.carshop.utils.Enum;

public enum PrivilegedUser {
    inventory_clerk(6), sales_person(11), service_writer(19), manager(98), owner(255);

    private int code;

    public int getCode() {
        return code;
    }

    PrivilegedUser(int code) {
        this.code = code;
    }

    public static PrivilegedUser TransferPrivilegedUser(String Authority) {
        switch (Authority) {
            case "InventoryClerk":
                return inventory_clerk;
            case "SalePerson":
                return sales_person;
            case "ServiceWriter":
                return service_writer;
            case "Manager":
                return manager;
            case "Owner":
                return owner;
            default:
                return null;
        }
    }

}
