package com.company.CityLodge.Controller;

import com.company.CityLodge.Model.DateTime;
import com.company.CityLodge.Model.Excepetion.RentDayNotCorrectExcepetion;
import com.company.CityLodge.View.CityLodgeApp;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class ReturnHandler implements EventHandler<ActionEvent> {
    private int index;
    private TextField textField3;

    public ReturnHandler(int index, TextField textField3) {
        this.index = index;
        this.textField3 = textField3;
    }

    @Override
    public void handle(ActionEvent event) {
        Boolean checkLength = (textField3.getText().length() == 10);
        if (checkLength) {
            Controller controller = CityLodgeApp.getController();

            DateTime temp = Controller.strToDate(textField3);
            try {
                controller.getHotel().getRoom(index).returnRoom(temp);
                controller.getHotel().getRoom(index).addRecord(controller.getHotel().getRoom(index).getTempRecord());
                controller.getHotel().getRoom(index).setTempRecord();
                controller.getHotel().updateData();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText(controller.getHotel().getRoom(index).getRoomId() + "is now being returned");
                alert.show();
            } catch (RentDayNotCorrectExcepetion e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(e.getMessage());
                alert.show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please check your input, date should be YYYY-MM-DD");
            alert.show();
        }
    }
}
