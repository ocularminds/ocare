/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ocularminds.ocare.qs;

/**
 *
 * @author text_
 */
public abstract class QueueEvent implements Comparable<QueueEvent> {

    protected long time;

    public QueueEvent(long t) {
        this.time = t;
    }

    public long getTime() {
        return time;
    }

    @Override
    public int compareTo(QueueEvent event) {
        if (time < event.getTime()) {
            return -1;
        } else if (time == event.getTime()) {
            return 0;
        } else {
            return 1;
        }
    }

}
