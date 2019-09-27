package com.company.CityLodge.Model;

import com.company.CityLodge.JavaDatabase;

import java.util.ArrayList;

import java.io.Serializable;

public class CityLodge implements Serializable{
    private ArrayList<Room> allRooms;
    private int numOfRooms;

    public void loadData(ArrayList<Room> room)
    {
        for(int i=0;i<room.size();i++)
        {
            allRooms.add(i,room.get(i));
            numOfRooms++;
        }
    }

    public void updateData()
    {
        JavaDatabase javaDatabase = new JavaDatabase();
        javaDatabase.updateRoom(allRooms);
    }

    public CityLodge() {
        allRooms = new ArrayList<Room>();
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

    public int getNumOfRooms()
    {
        return numOfRooms;
    }

}