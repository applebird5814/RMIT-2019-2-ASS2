package com.company.CityLodge.Controller;

import com.company.CityLodge.JavaDatabase;
import com.company.CityLodge.Model.CityLodge;
import com.company.CityLodge.Model.DateTime;
import javafx.scene.control.TextField;
import java.awt.*;

public class Controller {
    private CityLodge hotel = new CityLodge();
    private JavaDatabase database = new JavaDatabase();
    public Controller() {
        hotel.loadData(database.getRoom());
    }

    public CityLodge getHotel() {
        return hotel;
    }

    public JavaDatabase getDatabase() {
        return database;
    }

    public static DateTime strToDate(TextField textField)
    {
        int year = Integer.parseInt(textField.getText(0,4));
        int month = Integer.parseInt(textField.getText(5,7));
        int day = Integer.parseInt(textField.getText(8,10));
        DateTime temp = new DateTime(day,month,year);
        return temp;
    }

    public void updateData()
    {
        hotel.updateData();
    }
}
