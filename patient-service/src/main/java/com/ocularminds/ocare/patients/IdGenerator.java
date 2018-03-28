/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ocularminds.ocare;

import java.util.concurrent.atomic.AtomicLong;

/**
 *
 * @author Jejelowo B. Festus <festus.jejelowo@ocularminds.com>
 */
public class IdGenerator {

    private static final IdGenerator INSTANCE = new IdGenerator();

    private static AtomicLong sequence = new AtomicLong(1);

    private IdGenerator() {
    }

    public static IdGenerator getInstance() {
        return INSTANCE;
    }

    public String generate(Identifier.Type type) {
        String clockTime
                = new StringBuilder(Long.toString(System.currentTimeMillis())).reverse().toString();
        String threadId = String.format("%04d", Thread.currentThread().getId());
        String atomicId = String.format("%010d", sequence.getAndIncrement());
        String number;
        if (null == type) {
            return clockTime.substring(0, 2).concat(threadId.substring(2, 4))
                    .concat(atomicId.substring(4, 10));
        }
        switch (type) {
            case LONG:
                number = clockTime.substring(0, 6).concat(threadId).concat(atomicId);
                break;
            case MIN:
                number = clockTime.substring(0, 1).concat(threadId.substring(2, 4));
                number = number.concat(atomicId.substring(5, 10));
                break;
            default:
                number = clockTime.substring(0, 2).concat(threadId.substring(2, 4));
                number = number.concat(atomicId.substring(4, 10));
                break;
        }
        return number;
    }
}
