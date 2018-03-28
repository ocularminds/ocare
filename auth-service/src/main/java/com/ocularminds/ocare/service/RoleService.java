package com.ocularminds.ocare.service;

import com.ocularminds.ocare.error.DuplicateNameException;
import com.ocularminds.ocare.model.ResourceLink;
import com.ocularminds.ocare.model.Role;
import java.util.List;
import java.util.Optional;

/**
 * Created by text_ on 19/03/2018.
 */

public interface RoleService {

    Optional<Role> find(String id);
    
    Optional<Role> findByName(String name);

    /**
     *
     * @param list
     * @return
     * @throws DuplicateNameException
     */
    Role save(Role list) throws DuplicateNameException ;

    List<Role> findAll();

    void delete(String id);

    List<ResourceLink> getResources(String id);

    /**
     *
     * @param listId
     * @param entry
     * @return
     */
    ResourceLink add(String listId, ResourceLink entry);

    void delete(String listId, String entryId);
}
