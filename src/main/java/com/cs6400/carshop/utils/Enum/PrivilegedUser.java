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
            case "inventory_clerk":
                return inventory_clerk;
            case "sales_person":
                return sales_person;
            case "service_writer":
                return service_writer;
            case "manager":
                return manager;
            case "owner":
                return owner;
            default:
                return null;
        }
    }

}
