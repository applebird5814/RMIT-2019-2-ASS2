package com.company.CityLodge.Controller;

import com.company.CityLodge.Model.StandardRoom;
import com.company.CityLodge.View.CityLodgeApp;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;


public class AddStandardHandler implements EventHandler<ActionEvent> {

    private TextField textField1;
    private TextField textField2;
    private TextField textField3;

    public AddStandardHandler(TextField textField1, TextField textField2, TextField textField3) {
        this.textField1 = textField1;
        this.textField2 = textField2;
        this.textField3 = textField3;
    }

    @Override
    public void handle(ActionEvent event) {
        try {
            Controller controller = CityLodgeApp.getController();
            Boolean checkLength = (textField1.getText().length() == 3) && (textField3.getText().length() == 1);
            if (checkLength) {
                StandardRoom standardRoom = new StandardRoom("R_" + textField1.getText(), textField2.getText(), Integer.parseInt(textField3.getText()));
                controller.getDatabase().saveRoom(standardRoom);
                controller.getHotel().addRoom(standardRoom);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("R_" + textField1.getText() + "is now add successfully.");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please check your input, Room Number should be XXX, Bedrooms should not be empty.");
                alert.show();
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Room already exists.");
            alert.show();
        }
    }
}
