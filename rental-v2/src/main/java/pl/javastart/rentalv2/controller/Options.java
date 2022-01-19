package pl.javastart.rentalv2.controller;

public enum Options {
    SHOW_ALL_AVAILABLE("Show all devices available."), ADD_NEW_DEVICE("Add new device."), RENT_DEVICE("Rent device."),
    RETURN_DEVICE("Return devices."), ADD_CUSTOMER("Create new Customer."), EXIT("Exit.");

    private final String description;


    Options(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static Options getOptionByNumber(int number){
        if (number < 0 || number > Options.values().length - 1){
            throw new IllegalArgumentException();
        }
        return Options.values()[number];
    }
}
