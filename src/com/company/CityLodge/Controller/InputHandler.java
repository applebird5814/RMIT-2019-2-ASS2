package com.company.CityLodge.Controller;

import com.company.CityLodge.Model.*;
import com.company.CityLodge.View.CityLodgeApp;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;

public class InputHandler implements EventHandler<ActionEvent> {
    private Stage stage;

    public InputHandler(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void handle(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Choose a txt file");
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("text", "*.txt"));

        File file = fc.showOpenDialog(stage);
        // Here if do not choose a file， it will be a null file
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String str = null;
            String roomId = "";
            ArrayList<HiringRecord> records = new ArrayList<HiringRecord>();
            while ((str = br.readLine()) != null) {
                if (str.charAt(5) != ':') {
                    records.add(addRecord(str));
                } else {
                    if (records.size() != 0) {
                        Controller controller = CityLodgeApp.getController();
                        Collections.reverse(records);
                        controller.getHotel().getRoom(controller.getHotel().findRoom(roomId)).loadRecord(records);
                        controller.getHotel().updateData();
                        records.clear();
                    }
                    roomId = addRoom(str);
                }
                if (records.size() != 0) {
                    Controller controller = CityLodgeApp.getController();
                    Collections.reverse(records);
                    controller.getHotel().getRoom(controller.getHotel().findRoom(roomId)).loadRecord(records);
                    controller.getHotel().updateData();
                    records.clear();
                }
            }
            br.close();
            CityLodgeApp.getController().getHotel().textMaintainCheck();
            CityLodgeApp.getController().getHotel().updateData();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Import Data Success");
            alert.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public String addRoom(String s) {
        String a[] = s.split(":", -1);
        String roomId = a[0];
        int numOfBed = Integer.parseInt(a[1]);
        String roomType = a[2];
        int states = 0;
        if (a[3].equals("Available")) {
            states = 1;
        }
        if (a[3].equals("Maintenance")) {
            states = -1;
        }
        Controller controller = CityLodgeApp.getController();
        if (roomType.equals("Standard")) {
            String feature = a[4];
            try {
                StandardRoom standardRoom = new StandardRoom(roomId, feature, numOfBed);
                controller.getDatabase().saveRoom(standardRoom);
                standardRoom.setStates(states);
                controller.getHotel().addRoom(standardRoom);
            } catch (Exception e) {
                System.out.println("Same room");
            }
            try {
                String imageName = a[5];
                controller.getDatabase().addImage(roomId, imageName);
            } catch (Exception e) {
                System.out.println("Noimage");
            }
        }
        if (roomType.equals("Suite")) {
            String maintain = a[4];
            DateTime time = strToDate(maintain);
            String feature = a[5];
            try {
                Suite suite = new Suite(roomId, feature, time);
                suite.setStates(states);
                controller.getDatabase().saveRoom(suite);
                controller.getHotel().addRoom(suite);
            } catch (Exception e) {
                System.out.println("Same room");
            }
            try {
                String imageName = a[6];
                controller.getDatabase().addImage(roomId, imageName);
            } catch (Exception e) {
                System.out.println("Noimage");
            }
        }
        return roomId;
    }

    public HiringRecord addRecord(String s) {
        String b = null;
        HiringRecord hiringRecord = new HiringRecord(b);
        String a[] = s.split(":", -1);
        hiringRecord.setRecordID(a[0], 1);
        DateTime rentDate = strToDate(a[1]);
        hiringRecord.setRentDate(rentDate);
        DateTime estimateDate = strToDate(a[2]);
        hiringRecord.setEstRentDate(estimateDate);
        if (a[3].equals("none")) {
            hiringRecord.setRecordStage(1);
        } else {
            DateTime aclDate = strToDate(a[3]);
            hiringRecord.setAclRentDate(aclDate);
            double rentFee = Double.parseDouble(a[4]);
            double lateFee = Double.parseDouble(a[5]);
            hiringRecord.setRentalFee(rentFee);
            hiringRecord.setLateFee(lateFee);
            hiringRecord.setRecordStage(2);
        }
        return hiringRecord;
    }

    public DateTime strToDate(String s) {
        String a[] = s.split("/", -1);
        int day = Integer.parseInt(a[0]);
        int month = Integer.parseInt(a[1]);
        int year = Integer.parseInt(a[2]);
        DateTime time = new DateTime(day, month, year);
        return time;
    }
}
