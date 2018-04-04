/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ocularminds.ocare.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author text_
 */
public interface CapacityEnumerableRepository {

    List loadAll();

//    @Query("select r.name from User u,Role r,UserRole ur "
//            + "where  u.id=?1 and ur.userId = u.id "
//            + "and ur.roleId = r.id"
//    )
//    List<String> findRoleByUserId(String id);
}
