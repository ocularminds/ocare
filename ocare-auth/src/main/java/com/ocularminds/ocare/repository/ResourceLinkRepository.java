package com.ocularminds.ocare.repository;

import com.ocularminds.ocare.model.ResourceLink;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceLinkRepository extends JpaRepository<ResourceLink, String> {
    
    Optional<ResourceLink> findById(String id);

    List<ResourceLink> findAllByRoleId(String listId);
}
