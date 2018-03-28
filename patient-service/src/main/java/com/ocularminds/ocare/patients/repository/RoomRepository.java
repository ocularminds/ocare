package com.ocularminds.ocare.patients.repository;

import com.ocularminds.ocare.patients.model.Room;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, String> {

    Optional<Room> findById(String id);
    
    Optional<Room> findByName(String name);

    List<Room> findAllByWardId(String wardId);

    List<Room> findByType(String type);
}
