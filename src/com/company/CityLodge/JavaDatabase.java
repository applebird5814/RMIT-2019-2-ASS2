package com.company.CityLodge;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

import com.company.CityLodge.Model.*;

public class JavaDatabase {
    private static Connection conn;
    private PreparedStatement pres;
    static {
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            String url = "jdbc:hsqldb:file:src/com/company/CityLodge/Database/";
            String user = "s3690579";
            String password = "19941007";
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connection successful");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateRoom(ArrayList<Room> RoomList) {
        String sql = "update ROOM set OBJECT = ? where ROOMID like ?";
        try {

            for (int i = 0; i < RoomList.size(); i++) {
                pres = conn.prepareStatement(sql);
                pres.setObject(1, roomToBlob(RoomList.get(i)));
                pres.setObject(2, RoomList.get(i).getRoomId());
                pres.addBatch();
            }
            pres.executeBatch();
            conn.commit();
            if (pres != null) {
                pres.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveRoom(Room room) throws Exception{
        String sql = "insert into ROOM(roomid, object) values(?,?)";

        pres = conn.prepareStatement(sql);
        pres.setObject(1, room.getRoomId());
        pres.setObject(2, roomToBlob(room));
        pres.addBatch();
        pres.executeBatch();
        conn.commit();
        if (pres != null) {
            pres.close();
        }
    }

    public ArrayList<Room> getRoom() {
        ArrayList<Room> list = new ArrayList<Room>();
        String sql = "select OBJECT from ROOM";

        try {
            pres = conn.prepareStatement(sql);
            ResultSet res = pres.executeQuery();
            while (res.next()) {
                Blob inBlob = res.getBlob(1);

                InputStream is = inBlob.getBinaryStream();
                BufferedInputStream bis = new BufferedInputStream(is);

                byte[] bytes = new byte[(int) inBlob.length()];
                while (-1 != (bis.read(bytes, 0, bytes.length))) {
                    ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bytes));
                    Room room = (Room) in.readObject();

                    list.add(room);
                }
                bis.close();
                is.close();
            }
        } catch (SQLException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void  addImage(String roomId,String imageName)
    {
        String sql = "update ROOM set IMAGENAME = ? where ROOMID like ?";
        try {
            pres = conn.prepareStatement(sql);
            pres.setObject(1, imageName);
            pres.setObject(2, roomId);
            pres.executeUpdate();
            conn.commit();
            if (pres != null) {
                pres.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String imageName(String roomId)
    {
        String imageName = null;
        String sql = "select room.imageName from room where room.roomid like ?";
        try{
            pres = conn.prepareStatement(sql);
            pres.setObject(1, roomId);
            ResultSet res = pres.executeQuery();
            while(res.next())
            {
                imageName=res.getString("imageName");
            }
        }
         catch (SQLException e) {
             e.printStackTrace();
         }
        if(imageName==null)
        {
            imageName="No_image.png";
        }
        return imageName;
    }

    public byte[] roomToBlob(Object object)
    {
        try{
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ObjectOutputStream outputStream = new ObjectOutputStream(out);
            outputStream.writeObject(object);
            byte[] bytes = out.toByteArray();
            outputStream.close();
            out.close();
            return bytes;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

}