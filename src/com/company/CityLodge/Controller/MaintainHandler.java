package com.company.CityLodge.Controller;


import com.company.CityLodge.View.CityLodgeApp;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;

public class MaintainHandler implements EventHandler<ActionEvent> {
    private int index;

    public MaintainHandler(int index) {
        this.index = index;
    }

    @Override
    public void handle(ActionEvent event) {
        Controller controller = CityLodgeApp.getController();

        controller.getHotel().getRoom(index).performMaintenance();
        controller.getHotel().updateData();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(controller.getHotel().getRoom(index).getRoomId() + " is now being Maintenance");
        alert.show();

    }
}