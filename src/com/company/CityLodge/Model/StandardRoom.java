package com.company.CityLodge.Model;

import com.company.CityLodge.JavaDatabase;

public class StandardRoom extends Room {


    public StandardRoom(String inputroomID, String inputfeature, int inputnumOfBed) {
        super(inputroomID, inputfeature, "Standard", inputnumOfBed);
    }

    public String toString() {
        String s = super.toString() + ":" + super.getFeature();
        JavaDatabase javaDatabase = new JavaDatabase();
        String name = javaDatabase.imageName(super.getRoomId());
        if (name.equals("No_image.png")) {
            return s;
        } else {
            s = s + ":" + name;
        }
        return s;
    }


}