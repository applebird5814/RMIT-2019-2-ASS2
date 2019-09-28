package com.company.CityLodge.Model;

import java.io.Serializable;

public class HiringRecord implements Serializable,Cloneable {
    private String recordID;
    // basic detail means something will not change, for example, roomID, beds, features;
    private String basicDetail;
    private String states;
    private DateTime rentDate;
    private DateTime estRentDate;
    private DateTime aclRentDate;
    private String custom_Id;
    private double rentalFee;
    private double lateFee;

    // recordStage is used to mention the record process. 0 means not rented yet, 1 means being rented, 2 means returned.
    private int recordStage;


    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString()
    {
        String s = "";
        if(recordStage==2)
        {
            s=s+recordID+":"+rentDate+":"+estRentDate+":"+aclRentDate+":"+rentalFee+":"+lateFee;
        }
        if(recordStage==1)
        {
            s=s+recordID+":"+rentDate+":"+estRentDate+":none:none:none";
        }
        return s;
    }

    public String getDetail() {
        String detail = "";

        if (recordStage >= 1) {
            detail = detail + "RENTAL RECORD" + "\n" + "Record ID:             " + recordID + "\n" + "Rent Date:             " +
                    rentDate.getFormattedDate() + "\n" + "Estimated Return Date: " + estRentDate.getFormattedDate() + "\n";
        }
        if (recordStage == 2) {
            detail = detail + "Actual Return Date:\t\t" + aclRentDate.getFormattedDate() + "\n" + "Rental Fee:		\t\t" + rentalFee +
                    "\nLate Fee:\t\t\t\t" + lateFee + "\n";
        }
        return detail;
    }

    public String getBasicDetail() {
        return basicDetail;
    }

    public String getCustom_Id() {
        return custom_Id;
    }

    public void setRecordStage(int stage) {
        recordStage = stage;
    }

    public int getRecordStage() {
        return recordStage;
    }

    public void setRecordID(String roomId) {
        recordID = roomId + "_" + custom_Id + "_" + rentDate.getEightDigitDate();
    }

    public void setRecordID(String recordID,int i)
    {
        this.recordID = recordID;
    }
    public void setRentDate(DateTime rentDate) {
        this.rentDate = rentDate;
    }

    public void setEstRentDate(DateTime estRentDate) {
        this.estRentDate = estRentDate;
    }

    public String getStates() {
        return states;
    }

    public void setStates(String states) {
        this.states = states;
    }

    public HiringRecord(String basicDetail) {
        this.basicDetail = basicDetail;
        states = "Available";
    }

    public void setCustom_Id(String inputCustId) {
        custom_Id = inputCustId;
    }

    public void setRentalFee(double rentalFee) {
        rentalFee = ((double) ((int) (rentalFee * 100 + 0.5))) / 100;
        this.rentalFee = rentalFee;
    }

    public void setLateFee(double lateFee) {
        lateFee = ((double) ((int) (lateFee * 100 + 0.5))) / 100;
        this.lateFee = lateFee;
    }

    public void setAclRentDate(DateTime aclRentDate) {
        this.aclRentDate = aclRentDate;
    }

    public DateTime getRentDate() {
        return rentDate;
    }

    public DateTime getEstRentDate() {
        return estRentDate;
    }

    public void setBasicDetail(String basicDetail) {
        this.basicDetail = basicDetail;
    }
}