/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ocularminds.ocare.patients.service;

import com.ocularminds.ocare.patients.model.Room;
import com.ocularminds.ocare.patients.model.Ward;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Jejelowo .B. Festus <festus.jejelowo@ocularminds.com>
 */
public interface RoomService {
    
    public List<Ward> getWards();
    
    public List<Ward> findWardByType(String type);
    
    public Ward ward(String id);
    
    public Ward save(Ward ward);
    
    public void deleteWard(String id);
    
    public void update(Ward ward);
    
    public List<Room> getRooms();
    
    public List<Room> findByType(String type);
    
    public List<Room> findRoomByWardId(String wardId);
    
    public Room findByNumber(String number);
    
    public Room room(String id);
    
    public Room save(String wardId, Room room);
    
    public void delete(String wardId,String roomId);
    
    public void update(Room room);
}
