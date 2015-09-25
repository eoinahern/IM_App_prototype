package com.example.eoin_a.im_app20.Entitys;

/**
 * Created by eoin_a on 25/09/2015.
 */
public class Contact {



    private String email;
    private String name;
    private String number;


    public Contact(String emailin, String namein, String numberin)
    {
        name = namein;
        number = numberin;
        email = emailin;
    }

    public Contact(String namein, String numberin)
    {

        name = namein;
        number = numberin;
        email = "";

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
