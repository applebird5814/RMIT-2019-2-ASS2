package com.company.CityLodge.Model;

import com.company.CityLodge.JavaDatabase;

import java.io.Serializable;
import java.util.ArrayList;

public class CityLodge implements Serializable {
    private ArrayList<Room> allRooms;
    private int numOfRooms;

    public CityLodge() {
        allRooms = new ArrayList<Room>();
    }

    public void textMaintainCheck()
    {
        for(int i=0;i<allRooms.size();i++)
        {
            allRooms.get(i).checkMaintain();
        }
    }

    public void loadData(ArrayList<Room> room) {
        for (int i = 0; i < room.size(); i++) {
            try {
                room.get(i).checkMaintain();
                allRooms.add(i, (Room) room.get(i).clone());
                numOfRooms++;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void updateData() {
        JavaDatabase javaDatabase = new JavaDatabase();
        javaDatabase.updateRoom(allRooms);
    }

    public void addRoom(Room newRoom) {
        newRoom.setTempRecord();
        allRooms.add(numOfRooms, newRoom);
        numOfRooms++;
    }

    public int findRoom(String roomID) {
        int i;
        for (i = 0; i < numOfRooms; i++) {
            if (allRooms.get(i).getRoomId().equals(roomID)) {
                return i;
            }
        }
        return -1;
    }

    public Room getRoom(int index) {
        return allRooms.get(index);
    }

    public int getNumOfRooms() {
        return numOfRooms;
    }

}