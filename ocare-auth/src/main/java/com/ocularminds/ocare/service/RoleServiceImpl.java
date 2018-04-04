package com.ocularminds.ocare.service;

import com.ocularminds.ocare.error.DuplicateNameException;
import com.ocularminds.ocare.error.NotFoundException;
import com.ocularminds.ocare.model.ResourceLink;
import com.ocularminds.ocare.model.Role;
import com.ocularminds.ocare.repository.ResourceLinkRepository;
import com.ocularminds.ocare.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by festus.jejelowo@ocularminds.com on 19/03/2018.
 */
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository listRepository;

    private final ResourceLinkRepository resourceRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository listRep, ResourceLinkRepository entryRep) {

        this.listRepository = listRep;
        this.resourceRepository = entryRep;
    }

    @Override
    public Optional<Role> find(String id) {
        return listRepository.findById(id);
    }

    @Override
    public Optional<Role> findByName(String name) {
        return listRepository.findByName(name);
    }

    @Override
    public List<Role> findAll() {
        return listRepository.findAll();
    }

    @Override
    public void delete(String listId) {
        Role list = find(listId).orElseThrow(() -> new NotFoundException("Role not found "));
        listRepository.delete(list);
    }

    /**
     *
     * @param id
     * @param entryId
     */
    @Override
    @Transactional(rollbackFor={IllegalArgumentException.class, NotFoundException.class})
    public void delete(String id, String entryId) {
        Role list = find(id).orElseThrow(() -> new NotFoundException("Role not found "));
        ResourceLink entry = resourceRepository.findById(entryId)
                .orElseThrow(() -> new NotFoundException("ResourceLink not found "));
        if (entry.getRole() != list) {
            throw new IllegalArgumentException();
        }
        resourceRepository.delete(entry);
    }

    @Override
    public List<ResourceLink> getResources(String id) {
        return resourceRepository.findAllByRoleId(id);
    }

    @Override
    public Role save(Role list) throws DuplicateNameException {
        if (listRepository.findByName(list.getName()).isPresent()) {
            throw new DuplicateNameException("duplicate name not allowed");
        }
        return listRepository.save(list);
    }

    @Override
    @Transactional
    public ResourceLink add(String listId, ResourceLink entry) {
        Role list = find(listId).orElseThrow(() -> new NotFoundException("Role not found "));
        entry.setRole(list);
        return resourceRepository.save(entry);
    }
}
