package com.company.CityLodge.Model;

import com.company.CityLodge.Model.Excepetion.RentDayNotCorrectExcepetion;
import com.company.CityLodge.Model.Excepetion.ReturnDayNotCorrectExcepetion;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Room implements Serializable, Cloneable {

    private static long serialVersionUID = 20190926L;
    private String roomId;
    private String feature;
    private int states;
    // For states, we have 1 for Available, 0 for Rented and -1 for Maintenance
    private ArrayList<HiringRecord> allRecord;
    private HiringRecord tempRecord;
    private int numOfRecord;
    private int numOfBed;
    private String roomType;
    private DateTime nextMaintenanceDate;
    private DateTime maintenanceDate;

    public Room(String inputroomID, String inputfeature, String inputroomType, int numOfBed) {
        roomId = inputroomID;
        feature = inputfeature;
        states = 1;
        allRecord = new ArrayList<HiringRecord>();
        roomType = inputroomType;
        this.numOfBed = numOfBed;
        setTempRecord();
    }

    public void checkMaintain()
    {
        if(states==-1)
        {
            tempRecord.setStates("Maintenance");
        }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String exportRecord() {
        String s = "";
        if (tempRecord.getRecordStage() != 0) {
            s = s + tempRecord.toString() + "\n";
        }
        for (int i = numOfRecord - 1; i >= 0; i--) {
            s = s + allRecord.get(i).toString() + "\n";
        }
        return s;
    }

    public int getPrice(int numOfBed) {
        if (numOfBed == 1) {
            return 59;
        }
        if (numOfBed == 2) {
            return 99;
        }
        if (numOfBed == 4) {
            return 199;
        }
        if (numOfBed == 6) {
            return 999;
        }
        return 0;
    }

    public void setTempRecord() {
        String basicDetail = "Room ID: \t\t\t" + roomId + "\n" + "Number of bedrooms:\t" + numOfBed + "\n" + "Type:\t\t\t\t"
                + roomType + "\n" + "Feature summary:\t" + feature + "\n";
        tempRecord = new HiringRecord(basicDetail);
    }

    public void loadRecord(ArrayList<HiringRecord> list) {
        String basicDetail = "Room ID: \t\t\t" + roomId + "\n" + "Number of bedrooms:\t" + numOfBed + "\n" + "Type:\t\t\t\t"
                + roomType + "\n" + "Feature summary:\t" + feature + "\n";
        int i;
        for (i = 0; i < list.size(); i++) {
            try {
                HiringRecord temp = (HiringRecord) list.get(i).clone();
                temp.setBasicDetail(basicDetail);
                if(list.get(i).getRecordStage()==1)
                {
                    temp.setStates("Rented");
                    tempRecord=temp;
                }
                else
                {
                    addRecord(temp);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void addRecord(HiringRecord newRecord) {
        if (numOfRecord == 10) {
            numOfRecord--;
            allRecord.remove(0);
        }
        allRecord.add(numOfRecord, newRecord);
        numOfRecord++;
    }

    public int getStates() {
        return states;
    }

    public String getDetail() {
        String detail = roomId + states + feature;
        return detail;
    }

    public String getRoomId() {
        return roomId;
    }

    public void rent(String customerId, DateTime inputrentDate, int numOfRentDay) {
        Boolean daysCheck = true;
        if (roomType.equals("Standard")) {
            if (inputrentDate.getNameOfDay().equals("Saturday") || inputrentDate.getNameOfDay().equals("Sunday")) {
                daysCheck = (numOfRentDay <= 10) && (numOfRentDay >= 3);
            } else {
                daysCheck = (numOfRentDay <= 10) && (numOfRentDay >= 2);
            }
        }
        if (roomType.equals("Suite")) {
            DateTime tempDateTime = new DateTime(inputrentDate, numOfRentDay);
            daysCheck = (nextMaintenanceDate.getTime() > tempDateTime.getTime()) && (numOfRentDay <= 10);
        }
        if (daysCheck) {
            tempRecord.setRentDate(inputrentDate);
            tempRecord.setCustom_Id(customerId);
            DateTime estRentDate = new DateTime(tempRecord.getRentDate(), numOfRentDay);
            tempRecord.setEstRentDate(estRentDate);
            states = 0;
            tempRecord.setStates("Rented");
            tempRecord.setRecordID(roomId);
            tempRecord.setRecordStage(1);
        } else {
            throw new RentDayNotCorrectExcepetion("Rent day not correct");
        }
    }

    public void returnRoom(DateTime returnDate) {
        if ((returnDate.getTime() - tempRecord.getRentDate().getTime()) <= 0) {
            throw new ReturnDayNotCorrectExcepetion("Return day is eariler than rent day!");
        } else {
            tempRecord.setAclRentDate(returnDate);
            double dailyPrice = 0;
            double latePrice = 0;
            if (numOfBed == 1) {
                dailyPrice = 59;
            }
            if (numOfBed == 2) {
                dailyPrice = 99;
            }
            if (numOfBed == 4) {
                dailyPrice = 199;
            }
            latePrice = 1.35 * dailyPrice;
            if (numOfBed == 6) {
                dailyPrice = 999;
                latePrice = 1099;
            }
            long rentDay;
            long lateDay;
            rentDay = (returnDate.getTime() - tempRecord.getRentDate().getTime()) / 86400000;
            lateDay = (returnDate.getTime() - tempRecord.getEstRentDate().getTime()) / 86400000;
            double totalPrice;
            if (lateDay <= 0) {
                totalPrice = rentDay * dailyPrice;
                tempRecord.setRentalFee(totalPrice);
                tempRecord.setLateFee(0);
            } else {
                totalPrice = (rentDay - lateDay) * dailyPrice;
                tempRecord.setRentalFee(totalPrice);
                totalPrice = lateDay * latePrice;
                tempRecord.setLateFee(totalPrice);
            }
            tempRecord.setRecordStage(2);
            states = 1;
        }
    }

    public void performMaintenance() {
        tempRecord.setStates("Maintenance");
        states = -1;
    }

    public void completeMaintenance(DateTime completionDate) {
        tempRecord.setStates("Available");
        states = 1;
        maintenanceDate = completionDate;
        nextMaintenanceDate = new DateTime(maintenanceDate, 10);
    }


    protected void setNextMaintenanceDate(DateTime nextMaintenanceDate) {
        this.nextMaintenanceDate = nextMaintenanceDate;
    }

    public HiringRecord getTempRecord() {
        return tempRecord;
    }

    public String showAllRecord() {
        String fullRecord = tempRecord.getBasicDetail() + "States:\t\t\t\t" + tempRecord.getStates() + "\n";
        int i;
        if ((numOfRecord == 0) && (tempRecord.getRecordStage() == 0)) {
            fullRecord = fullRecord + "RENTAL RECORD: \t\tempty\n";
        }
        if (tempRecord.getRecordStage() != 2) {
            fullRecord = fullRecord + tempRecord.getDetail();
        }
        for (i = numOfRecord - 1; i >= 0; i--) {
            fullRecord = fullRecord + "--------------------------------------\n" + allRecord.get(i).getDetail();
        }
        return fullRecord;
    }

    public String getFeature() {
        return feature;
    }

    public int getNumOfBed() {
        return numOfBed;
    }

    public String getRoomType() {
        return roomType;
    }

    @Override
    public String toString() {
        String state = null;
        if (states == 1) {
            state = "Available";
        }
        if (states == 0) {
            state = "Rented";
        }
        if (states == -1) {
            state = "Maintenance";
        }
        String s = roomId + ":" + numOfBed + ":" + roomType + ":" + state;
        return s;
    }

    public DateTime getMaintenanceDate() {
        return maintenanceDate;
    }

    public void setMaintenanceDate(DateTime maintenanceDate) {
        this.maintenanceDate = maintenanceDate;
    }

    public void setStates(int states) {
        this.states = states;
    }
}