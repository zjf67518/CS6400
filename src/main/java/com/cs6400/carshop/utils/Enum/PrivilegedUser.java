package com.cs6400.carshop.utils.Enum;

public enum PrivilegedUser {
    inventory_clerk(6), sales_person(11), service_writer(19), manager(34), owner(255);

    private int type;

    public int getType() {
        return type;
    }

    PrivilegedUser(int type) {
        this.type = type;
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
