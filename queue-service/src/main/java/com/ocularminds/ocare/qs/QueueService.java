/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ocularminds.ocare.qs;

import com.google.gson.Gson;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author text_
 */
public class QueueService {

    String $dbname = "";
    String $qid = "";
    String sql = "SELECT qtran.QueueID,queueno,CustID,arrive,tstatus,qqueue.AvgTim e "
            + "FROM qtran INNER JOIN qqueue ON qtran.QueueID = qqueue.QueueID "
            + "WHERE qtran.QueueID ='" + $qid + "'";

    static int[] arrival = {3, 3, 9};
    static int[] service = {2, 15, 14};

    int waitTime;
    int serviceTime;

    Queue<QueueEntrant> records = new LinkedList<>();

    public int max() {
        int maxWaitTime = 0;
        int[] finishTime = new int[arrival.length];
        for (int i = 0; i < arrival.length; i++) {
            if (i != 0) {
                waitTime = finishTime[i - 1] - arrival[i];
                if (waitTime > maxWaitTime) {
                    maxWaitTime = waitTime;
                }
            }else{
                waitTime = 0;
            }
            finishTime[i] = arrival[i] + service[i];
        }
        return maxWaitTime;
    }

    public int maxWait(int[] arrival, int[] service) {
        int maxWaitTime = 0;
        int[] finishTime = new int[arrival.length];

        for (int i = 0; i < arrival.length; i++) {
            int startTime;
            if (i == 0) {
                startTime = arrival[i];
                System.out.println("start-time: " + startTime);
            } else {
                startTime = Math.max(arrival[i], finishTime[i - 1]);
            }
            finishTime[i] = startTime + service[i];
            waitTime = finishTime[i] - service[i] - arrival[i];
            if (waitTime > maxWaitTime) {
                maxWaitTime = waitTime;
            }
            System.out.println("start-time: " + startTime);
        }
        return maxWaitTime;
    }

    public int maxWait(int[] arrival, int mean) {
        int maxWaitTime = 0;
        int[] finishTime = new int[arrival.length];

        for (int i = 0; i < arrival.length; i++) {
            int startTime;
            if (i == 0) {
                startTime = arrival[i];
                System.out.println("start-time: " + startTime);
            } else {
                startTime = Math.max(arrival[i], finishTime[i - 1]);
            }
            finishTime[i] = startTime + service[i];
            waitTime = finishTime[i] - service[i] - arrival[i];
            if (waitTime > maxWaitTime) {
                maxWaitTime = waitTime;
            }
            System.out.println("start-time: " + startTime);
        }
        return maxWaitTime;
    }
//
//    public static void main(String[] args) {
//        QueueService q = new QueueService();
//        //int max1 = q.maxWait(arrival, service);
//        int max2 = q.max();
//        //System.out.println("Maximum wait time1 is: " + max1);
//        System.out.println("Maximum wait time2 is: " + max2);
//    }

    //printjson_encode($rows);
    @SuppressWarnings("unchecked")
    public void drawers(String json) {
        try {
            List<QueueEntrant> entrants = new Gson().fromJson(json, List.class);
            for (int i = 0; i < entrants.size(); i++) {
                QueueEntrant entrant = entrants.get(i);
                System.out.println("mean-time: " + Integer.valueOf(entrant.getMeanTime()) * 60 * (i + 1));
                System.out.println("mean-time: " + Integer.valueOf(entrant.getMeanTime()) * 60 * (i + 1));
            }
        } catch (Exception e) {
        }
        //arrival. total time. number in queve
    }
}
