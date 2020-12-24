package com.example.meghal.vsp_online;

/**
 * Created by Meghal on 26-02-2017.
 */

public class UserInformation {
    String service;
    String address;
    String phone;
    String pri;
    String usr;

    public UserInformation(){

    }

    public UserInformation(String user, String service, String address, String phone, String price) {
        this.usr = user;
        this.service = service;
        this.address = address;
        this.phone = phone;
        this.pri = price;
    }
}
