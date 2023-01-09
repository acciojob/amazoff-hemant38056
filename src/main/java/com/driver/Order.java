package com.driver;

public class Order {

    private String id;
    private int deliveryTime;

    public Order(String id, String deliveryTime) {

        // The deliveryTime has to converted from string to int and then stored in the attribute
        //deliveryTime  = HH*60 + MM
        this.id = id;

        String hour = deliveryTime.substring(0, 2);
        int hh = Integer.parseInt(hour);

        String minute = deliveryTime.substring(3, 5);
        int mm = Integer.parseInt(minute);
        this.deliveryTime = (hh * 60) + mm;
    }

    public String getId() {
        return id;
    }

    public int getDeliveryTime() {return deliveryTime;}

}
