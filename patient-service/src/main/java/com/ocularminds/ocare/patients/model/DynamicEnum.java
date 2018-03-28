/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ocularminds.ocare.patients.model;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

/**
 *
 * @author text_
 */
public interface DynamicEnum<E, D> extends Comparator {

    boolean exists(E enumValue);

    E valueOf(String name);

    List values();

    int ordinal(E enumValue);

    Set range(E from, E to);

    D backingValueOf(E enumValue);
}
