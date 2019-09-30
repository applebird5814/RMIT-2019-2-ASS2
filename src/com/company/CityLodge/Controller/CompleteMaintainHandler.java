package com.company.CityLodge.Controller;

import com.company.CityLodge.Model.DateTime;
import com.company.CityLodge.View.CityLodgeApp;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class CompleteMaintainHandler implements EventHandler<ActionEvent> {
    private int index;
    private TextField textField3;

    public CompleteMaintainHandler(int index, TextField textField3) {
        this.index = index;
        this.textField3 = textField3;
    }

    @Override
    public void handle(ActionEvent event) {
        Boolean checkLength = (textField3.getText().length() == 10);
        if (checkLength) {
            Controller controller = CityLodgeApp.getController();
            DateTime temp = Controller.strToDate(textField3);
            controller.getHotel().getRoom(index).completeMaintenance(temp);
            controller.getHotel().updateData();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(controller.getHotel().getRoom(index).getRoomId() + " has all maintenance operations completed and is now ready for rent.");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please check your input, date should be YYYY-MM-DD");
            alert.show();
        }
    }
}
