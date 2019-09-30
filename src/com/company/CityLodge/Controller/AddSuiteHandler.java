package com.company.CityLodge.Controller;

import com.company.CityLodge.Model.DateTime;
import com.company.CityLodge.Model.Suite;
import com.company.CityLodge.View.CityLodgeApp;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class AddSuiteHandler implements EventHandler<ActionEvent> {

    private TextField textField1;
    private TextField textField2;
    private TextField textField3;

    public AddSuiteHandler(TextField textField1, TextField textField2, TextField textField3) {
        this.textField1 = textField1;
        this.textField2 = textField2;
        this.textField3 = textField3;
    }

    @Override
    public void handle(ActionEvent event) {
        try {
            Controller controller = CityLodgeApp.getController();
            Boolean checkLength = (textField1.getText().length() == 3) && (textField3.getText().length() == 10);
            if (checkLength) {
                String roomId = "S_" + textField1.getText();
                String feature = textField2.getText();
                DateTime temp = Controller.strToDate(textField3);
                Suite suite = new Suite(roomId, feature, temp);
                controller.getDatabase().saveRoom(suite);
                controller.getHotel().addRoom(suite);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText(roomId + "is now add successfully.");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please check your input, date should be YYYY-MM-DD, Room Number should be XXX");
                alert.show();
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Room already exists.");
            alert.show();
        }
    }
}
