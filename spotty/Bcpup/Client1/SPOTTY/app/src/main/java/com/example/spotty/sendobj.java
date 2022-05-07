package com.example.spotty;

public class sendobj {

    String gender;
    String colourtone;
    String colour;
    String compartment;
    String building;

    public sendobj(String gender, String colourtone, String colour, String compartment, String building) {
        gender = gender;
        colourtone = colourtone;
        colour = colour;
        compartment = compartment;
        building = building;
    }

    public String getGender() {
        return gender;
    }

    public String getCtone() {
        return colourtone;
    }

    public String getColour() {
        return colour;
    }

    public String getCompartment() {
        return compartment;
    }

    public String getBuilding() {
        return building;
    }
}







