package com.company.CityLodge.Controller;

import com.company.CityLodge.Model.DateTime;
import com.company.CityLodge.Model.Excepetion.RentDayNotCorrectExcepetion;
import com.company.CityLodge.View.CityLodgeApp;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class RentHandler implements EventHandler<ActionEvent> {
    private int index;
    private TextField textField2;
    private TextField textField3;
    private TextField textField4;

    public RentHandler(int index, TextField textField2, TextField textField3, TextField textField4) {
        this.index = index;
        this.textField2 = textField2;
        this.textField3 = textField3;
        this.textField4 = textField4;
    }

    @Override
    public void handle(ActionEvent event) {
        Boolean checkLength =(textField3.getText().length() == 10);
        if(checkLength) {
            Controller controller = CityLodgeApp.getController();
            String custom = textField2.getText();

            DateTime temp = Controller.strToDate(textField3);
            int rentDays = Integer.parseInt(textField4.getText());
            try{
                controller.getHotel().getRoom(index).rent(custom,temp,rentDays);
                controller.getHotel().updateData();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText(controller.getHotel().getRoom(index).getRoomId()+ " is now being rent by"+custom);
                alert.show();
            }catch (RentDayNotCorrectExcepetion e)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(e.getMessage());
                alert.show();
            }
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please check your input, date should be YYYY-MM-DD");
            alert.show();
        }
    }
}
