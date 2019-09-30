package com.company.CityLodge.Model;

import com.company.CityLodge.JavaDatabase;

public class Suite extends Room {

    public Suite(String inputroomID, String inputfeature, DateTime inputmaintenanceDate) {
        super(inputroomID, inputfeature, "Suite", 6);
        super.setMaintenanceDate(inputmaintenanceDate);
        DateTime nextMaintenanceDate = new DateTime(inputmaintenanceDate, 10);
        super.setNextMaintenanceDate(nextMaintenanceDate);
    }

    @Override
    public String toString() {
        String s = super.toString() + ":" + super.getMaintenanceDate().toString() + ":" + super.getFeature();
        try {
            JavaDatabase javaDatabase = new JavaDatabase();
            String name = javaDatabase.imageName(super.getRoomId());
            if (name.equals("No_image.png")) {
                return s;
            } else {
                s = s + ":" + name;
            }
        } catch (Exception e) {

        }
        return s;
    }

}