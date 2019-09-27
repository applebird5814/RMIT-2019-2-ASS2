package com.company.CityLodge.Model;

import java.io.*;
import java.util.Scanner;
import com.company.CityLodge.*;
public class CityLodgeMain implements Serializable {
    public static void main(String[] args) {
        CityLodge hotel = new CityLodge();
        //hotel = load();
        int i = 0;
        JavaDatabase database = new JavaDatabase();
        hotel.loadData(database.getRoom());
        //database.delateAll();
/*
        for (; ; ) {
            Scanner scan = new Scanner(System.in);
            System.out.printf("**** CITYLODGE MAIN MENU ****\nAdd Room:				1\nRent Room:				2\n" +
                    "Return Room:			3\nRoom Maintenance:		4\nComplete Maintenance:   5\n" +
                    "Display All Rooms:		6\nExit Program:			7\nEnter your choice:");
            if(scan.hasNextInt())
            {
                i = scan.nextInt();
                if (i < 1 || i > 7) {
                    System.out.println("Please enter a integer between 1 and 7");
                }
                // fouction add room
                if (i == 1) {
                    // choose room type
                    System.out.println("Please enter 1 for adding a Standard Room and 2 for adding a suite");
                    int j, roomNumber;
                    if(scan.hasNextInt())
                    {
                        j = scan.nextInt();
                        if (j != 1 && j != 2) {
                            System.out.println("Please enter 1 or 2");
                        } else {
                            System.out.println("Please enter the room number(Room number should be 3 digit)");
                            if(scan.hasNextInt())
                            {
                                roomNumber = scan.nextInt();
                                if (roomNumber < 0 || roomNumber > 999) {
                                    System.out.println("Error, please enter the room number between 000 and 999");
                                } else {
                                    String roomId;

                                    // make the room ID�?03d means 3 digit integer fill with 0 at the front
                                    if (j == 1) {
                                        roomId = "R_" + String.format("%03d", roomNumber);
                                    } else {
                                        roomId = "S_" + String.format("%03d", roomNumber);
                                    }

                                    // check the room is exist or not
                                    int check = hotel.findRoom(roomId);
                                    if (check != -1) {
                                        System.out.printf("Error,this room %s is already exist\n", roomId);
                                    } else {
                                        //up to here, roomId is correct
                                        String feature;
                                        int numOfBed;
                                        System.out.println("Please enter a feature of this room");
                                        feature = scan.next();
                                        if (feature.length() > 100) {
                                            System.out.println("Feature is too long, it can not be more than 20 words");
                                        } else {
                                            //add a standard room
                                            if (j == 1) {
                                                System.out.println("Please choose the number of bed in the room, it can be 1,2 or 4");
                                                if(scan.hasNextInt()) {
                                                    numOfBed = scan.nextInt();
                                                    if (numOfBed != 1 && numOfBed != 2 && numOfBed != 4) {
                                                        System.out.println("Error, the number of beds can only be 1 2 or 4");
                                                    } else {
                                                        StandardRoom roomTemp = new StandardRoom(roomId, feature, numOfBed);
                                                        hotel.addRoom(roomTemp);
                                                        System.out.printf("Succeed in adding Room %s to the System\n", roomId);
                                                        JavaDatabase db = new JavaDatabase();
                                                        db.saveRoom(roomTemp);
                                                    }
                                                }
                                                else {
                                                    System.out.println("Error, the number of beds can only be 1 2 or 4");
                                                    scan.next();
                                                }
                                            } else
                                            // add a suite
                                            {
                                                System.out.println("Please enter the last maintenance date");
                                                DateTime maintenanceDate = inputDate();
                                                Suite roomTemp = new Suite(roomId, feature, maintenanceDate);
                                                hotel.addRoom(roomTemp);
                                                System.out.printf("Succeed in adding Room %s to the System!\n", roomId);
                                                JavaDatabase db = new JavaDatabase();
                                                db.saveRoom(roomTemp);
                                            }
                                        }
                                    }
                                }
                            }
                            // avoid string when ask for a int
                            else
                            {
                                System.out.println("Error, please enter the room number between 000 and 999");
                                scan.next();
                            }
                        }
                    }else {
                        System.out.println("Please enter 1 or 2");
                        scan.next();
                    }
                }
                // fouction rent room
                if (i == 2) {
                    String roomId;
                    System.out.println("Please enter the room id for rent(it should be R_XXX or S_XXX, X is 0 - 9)");
                    roomId = scan.next();
                    int index = hotel.findRoom(roomId);
                    if (index == -1) {
                        System.out.printf("The room id %s did not exist, please check again\n", roomId);
                    } else {
                        if (hotel.getRoom(index).getStates() != 1) {
                            System.out.printf("Room %s is not available at the moment.\n", roomId);
                        } else {
                            String custId;
                            System.out.println("Please enter the custom ID");
                            custId = scan.next();
                            System.out.println("Please enter the rent date");
                            DateTime rentDate = inputDate();
                            int rentDay;
                            System.out.println("How many days?:");
                            rentDay = scan.nextInt();
                            Boolean tryRentRoom = hotel.getRoom(index).rent(custId, rentDate, rentDay);
                            if (tryRentRoom) {
                                System.out.printf("Room %s is now rented by customer %s\n", roomId, custId);
                            } else {
                                System.out.println("Rent days is not correct, please check again.");
                            }
                        }
                    }
                }

                //return Room
                if (i == 3) {
                    String roomId;
                    System.out.println("Please enter the room id for return(it should be R_XXX or S_XXX, X is 0 - 9)");
                    roomId = scan.next();
                    int index = hotel.findRoom(roomId);
                    if (index == -1) {
                        System.out.printf("The room id %s did not exist, please check again\n", roomId);
                    } else {
                        if (hotel.getRoom(index).getStates() != 0) {
                            System.out.printf("Room %s has not been rented.\n", roomId);
                        } else {
                            System.out.println("Please enter the return date");
                            DateTime returnDate = inputDate();
                            Boolean tryReturn = hotel.getRoom(index).returnRoom(returnDate);
                            if (tryReturn) {
                                hotel.getRoom(index).addRecord(hotel.getRoom(index).getTempRecord());
                                hotel.getRoom(index).setTempRecord();
                            }
                        }
                    }
                }

                if (i == 4) {
                    String roomId;
                    System.out.println("Please enter the room id for maintenance(it should be R_XXX or S_XXX, X is 0 - 9)");
                    roomId = scan.next();
                    int index = hotel.findRoom(roomId);
                    if (index == -1) {
                        System.out.printf("The room id %s did not exist, please check again\n", roomId);
                    } else {
                        if (hotel.getRoom(index).performMaintenance()) {
                            System.out.printf("Room %s is now being Maintenance\n", roomId);
                        } else {
                            System.out.printf("Room %s is not availible for Maintenance, please check again\n", roomId);
                        }
                    }
                }

                if (i == 5) {
                    String roomId;
                    System.out.println("Please enter the room id for complete maintenance(it should be R_XXX or S_XXX, X is 0 - 9)");
                    roomId = scan.next();
                    int index = hotel.findRoom(roomId);
                    if (index == -1) {
                        System.out.printf("The room id %s did not exist, please check again\n", roomId);
                    } else {
                        System.out.println("Please enter the complete date");
                        DateTime completeTime = inputDate();
                        if (hotel.getRoom(index).completeMaintenance(completeTime)) {
                            System.out.printf("Room %s has all maintenance operations completed and is now ready for rent.\n", roomId);
                        } else {
                            System.out.printf("Room %s is not in Maintenance, please check again\n", roomId);
                        }
                    }
                }

                if (i == 6) {
                    int k;
                    for(k=0;k<hotel.getNumOfRooms();k++) {
                        System.out.println(hotel.getRoom(k).showAllRecord());
                    }
                }
            }
            else
            {
                System.out.println("Please entre a number!");
                scan.next();
            }

            if(i==7)
            {
                hotel.updateData();
                break;
            }
        }
*/


    }


    static DateTime inputDate() {
        int year=0;
        int month=0;
        int day=0;
        int i=1;
        Scanner scandate = new Scanner(System.in);
        for(;i==1;)
        {
            System.out.println("Please enter the year");
            if(scandate.hasNextInt())
            {
                year = scandate.nextInt();
                break;
            }
            else
            {
                System.out.println("Please enter a integer");
                scandate.next();
            }
        }
        for(;i==1;)
        {
            System.out.println("Please enter the month");
            if(scandate.hasNextInt())
            {
                month = scandate.nextInt();
                break;
            }
            else
            {
                System.out.println("Please enter a integer");
                scandate.next();
            }
        }
        for(;i==1;)
        {
            System.out.println("Please enter the day");
            if(scandate.hasNextInt())
            {
                day = scandate.nextInt();
                break;
            }
            else
            {
                System.out.println("Please enter a integer");
                scandate.next();
            }
        }

        DateTime temp = new DateTime(day, month, year);
        return temp;
    }
}