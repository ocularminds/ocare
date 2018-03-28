/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ocularminds.ocare.patients.service;

import com.ocularminds.ocare.error.DuplicateIdentityException;
import com.ocularminds.ocare.error.NotFoundException;
import com.ocularminds.ocare.patients.model.Room;
import com.ocularminds.ocare.patients.model.Ward;
import com.ocularminds.ocare.patients.repository.RoomRepository;
import com.ocularminds.ocare.patients.repository.WardRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jejelowo .B. Festus <festus.jejelowo@ocularminds.com>
 */
@Service
public class RoomServiceImpl implements RoomService {

    private final WardRepository wardRepository;
    private final RoomRepository roomRepository;

    @Autowired
    public RoomServiceImpl(WardRepository wards, RoomRepository rooms) {
        this.wardRepository = wards;
        this.roomRepository = rooms;
    }

    @Override
    public List<Ward> getWards() {
        return wardRepository.findAll();
    }

    @Override
    public List<Ward> findWardByType(String type) {
        return wardRepository.findByType(type);
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Ward ward(String id) {
        return wardRepository.findById(id).orElseThrow(
                () -> new NotFoundException(notFound("Ward:", id))
        );
    }

    @Override
    public Ward save(Ward ward) {
        if (wardRepository.findByName(ward.getName()).isPresent()) {
            throw new DuplicateIdentityException("Ward name " + ward.getName() + "  exists.");
        }
        return wardRepository.save(ward);
    }

    @Override
    public void deleteWard(String id) {
        Ward ward = ward(id);
        wardRepository.delete(ward);
    }

    @Override
    public void update(Ward input) {
        Ward ward = ward(input.getId());
        ward.setName(input.getName());
        ward.setSurcharge(input.getSurcharge());
        ward.setType(input.getType());
        wardRepository.save(ward);
    }

    @Override
    public List<Room> getRooms() {
        return roomRepository.findAll();
    }

    @Override
    public List<Room> findByType(String type) {
        return roomRepository.findByType(type);
    }

    @Override
    public List<Room> findRoomByWardId(String wardId) {
        return roomRepository.findAllByWardId(wardId);
    }

    @Override
    public Room findByNumber(String number) {
        return roomRepository.findById(number).orElseThrow(
                () -> new NotFoundException(notFound("Room number :", number))
        );
    }

    @Override
    public Room room(String id) {
        return roomRepository.findById(id).orElseThrow(
                () -> new NotFoundException(notFound("Room: ", id))
        );
    }

    @Override
    public Room save(String wardId, Room room) {
        if(roomRepository.findByName(wardId).isPresent()){
            throw new DuplicateIdentityException("Room name exists already.");
        }
        Ward ward = ward(wardId);
        room.setWard(ward);
        return roomRepository.save(room);
    }

    @Override
    public void delete(String wardId,String roomId) {                
        Ward ward = ward(wardId);
        Room room = room(roomId);
        if(!room.getWard().equals(ward)) throw new IllegalArgumentException();
        roomRepository.delete(room);
    }

    @Override
    public void update(Room rm) {
        Room room = room(rm.getId());
        roomRepository.save(room);
    }

    private String notFound(String object, String id) {
        return String.format("%s with id %s not found.", object, id);
    }
}
