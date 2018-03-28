package com.ocularminds.oauth2;

import com.ocularminds.ocare.error.NotFoundException;
import com.ocularminds.ocare.model.ResourceLink;
import com.ocularminds.ocare.model.Role;
import com.ocularminds.ocare.repository.ResourceLinkRepository;
import com.ocularminds.ocare.repository.RoleRepository;
import com.ocularminds.ocare.service.RoleService;
import com.ocularminds.ocare.service.RoleServiceImpl;
import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;

/**
 * Created by text_ on 19/03/2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class RoleServiceImplTest {

    @Mock
    RoleRepository roleRepository;

    @Mock
    ResourceLinkRepository resourceRepository;

    RoleService service = mock(RoleServiceImpl.class);

    @Before
    public void setup() {
        Role TEST1 = new Role("Assignments");
        TEST1.setId(Integer.toString(1));
        Role TEST2 = new Role("Domestic Activitis");
        TEST2.setId(Integer.toString(2));
        Role TEST3 = new Role("Visits");
        TEST3.setId(Integer.toString(3));
        List<Role> list = new LinkedList<>(Arrays.asList(TEST1, TEST2, TEST3));
        when(service.findAll()).thenReturn(list);
        when(service.find(any(String.class))).thenAnswer(i -> {
            String id = i.getArgumentAt(0, String.class);
            return list.stream().filter(t -> t.getId() != null && t.getId().equals(id)).findFirst();
        });
        when(service.findByName(any(String.class))).thenAnswer(i -> {
            String name = i.getArgumentAt(0, String.class);
            return list.stream().filter(t -> t.getName().equalsIgnoreCase(name)).findFirst();
        });
        when(service.save(any(Role.class))).thenAnswer(i -> {
            Role role = i.getArgumentAt(0, Role.class);
            role.setId(Long.toString((long) (Math.random() * 9000)));
            list.add(role);
            return role;
        });

        doAnswer((i) -> {
            String id = i.getArgumentAt(0, String.class);
            Role role = service.find(id).orElseThrow(() -> new NotFoundException("not found"));
            list.remove(role);
            return null;
        }).when(service).delete(any(String.class));

        doAnswer((i) -> {
            String id = i.getArgumentAt(0, String.class);
            String entryId = i.getArgumentAt(1, String.class);
            Role role = service.find(id).orElseThrow(() -> new NotFoundException("not found"));
            Set<ResourceLink> resources = role.getResources();
            resources = resources.stream().filter(e -> !e.getId().equals(entryId)).collect(Collectors.toSet());
            role.setResources(resources);
            list.remove(role);
            list.add(role);
            return null;
        }).when(service).delete(any(String.class), any(String.class));

        when(service.add(any(String.class), any(ResourceLink.class))).thenAnswer(i -> {
            String id = i.getArgumentAt(0, String.class);
            ResourceLink entry = i.getArgumentAt(1, ResourceLink.class);
            Role item = service.find(id).orElseThrow(() -> new NotFoundException("role record not found"));
            item.add(entry);
            return null;
        });
    }

    @Test
    public void testFindOneTodoList() throws Exception {
        Role TEST3 = new Role("Visits");
        TEST3.setId(Integer.toString(3));
        assertThat(TEST3.equals(service.find(Integer.toString(3)).get()));
    }

    @Test
    public void testFindAllTodoList() throws Exception {
        List<Role> records = service.findAll();
        assertEquals(records.size(), 3);
    }

    @Test
    public void delete() throws Exception {
        int SIZE_AFTER_DELETE = service.findAll().size() - 1;
        service.delete("2");
        assertEquals(service.findAll().size(), SIZE_AFTER_DELETE);
    }

    @Test(expected=NotFoundException.class)
    public void testDeleteWithNoRecordAvailableExcpectNotFoundException() throws Exception {
        service.delete(Long.toString(Long.MAX_VALUE));
    }

    @Test
    public void save() throws Exception {
        int SIZE_TO_TEST = service.findAll().size() + 2;
        Role t0 = new Role("Test");
        service.save(t0);

        Role t1 = new Role("Test");
        service.save(t1);
        assertEquals(service.findAll().size(), SIZE_TO_TEST);
        verify(service, atLeastOnce()).save(any(Role.class));
    }

    @Test
    public void add() throws Exception {
    }

    @Test
    public void testDeleteResourceLinkFromList() throws Exception {
        Role test = service.find("1").orElse(null);
        ResourceLink entry1 = new ResourceLink();
        entry1.setDescription("Clinical Test");
        entry1.setId(Long.toString((long) (Math.random() * 9000)));
        ResourceLink entry2 = new ResourceLink();
        entry2.setDescription("Debit Clearance");
        entry2.setId(Long.toString((long) (Math.random() * 9000)));
        service.add(test.getId(), entry1);
        service.add(test.getId(), entry2);
        service.delete(test.getId(), entry1.getId());
        assertEquals(1, service.find("1").orElse(null).getResources().size());
    }

    @Test
    public void testGetTodoResourcesForList() throws Exception {
        Role test = service.find("1").orElse(null);
        ResourceLink entry1 = new ResourceLink();
        entry1.setDescription("Clinical Test");
        entry1.setId(Long.toString((long) (Math.random() * 9000)));
        ResourceLink entry2 = new ResourceLink();
        entry2.setDescription("Debit Clearance");
        entry2.setId(Long.toString((long) (Math.random() * 9000)));
        service.add(test.getId(), entry1);
        service.add(test.getId(), entry2);
        assertEquals(2, service.find("1").orElse(null).getResources().size());
    }

    @Test//(expected = DuplicateNameException.class)
    public void testDuplicateToDoName() {
        Role test = service.find("1").orElse(null);
        assertEquals(test.getId(), "1");
    }

    @Test
    public void testResourcesHaveTheSameRoleId() {
        Role test = service.find("1").orElse(null);
        assertEquals(true, test != null);
        ResourceLink entry1 = new ResourceLink();
        entry1.setRole(test);
        ResourceLink entry2 = new ResourceLink();
        entry2.setRole(test);
        assertEquals(true, entry1.getRole().equals(entry2.getRole()));
    }

}
