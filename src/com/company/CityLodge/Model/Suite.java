package com.company.CityLodge.Model;

public class Suite extends Room {

    public Suite(String inputroomID, String inputfeature, DateTime inputmaintenanceDate) {
        super(inputroomID, inputfeature, "Suite", 6);
        super.setMaintenanceDate(inputmaintenanceDate);
        DateTime nextMaintenanceDate = new DateTime(inputmaintenanceDate, 10);
        super.setNextMaintenanceDate(nextMaintenanceDate);
    }

}