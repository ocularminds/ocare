/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ocularminds.ocare.qs;

/**
 * Simple "next-event" model process from hospitals text. The Future Event List
 * is kept as a PriorityQueue to allow it to be maintained by the next closest
 * event (or smallest into the future).
 *
 * @author text_
 */
import com.ocularminds.ocare.qs.model.OutPatient;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;


//use WebSocket to Notify - doctor,patient,consultants
//on Queue status
public class QueueProcessor {

    /**
     * The event list is maintained with a Priority Queue.
     */
    private final PriorityQueue<QueueEvent> FEL;

    /**
     * The patient queue is a regular queue.
     */
    private final Queue<OutPatient> patients;

    /** ArrayList used to store visits after they finish in the store.
    * Note: that it is not really necessary to do this in order to obtain our results.
    * Cumulative stats as the visits exit could be kept and output of each
    * patient's data could be saved to database (ex: to a file) as he/she exits as well.
    *
    * However, putting them into an ArrayList allows us to deal with them all in a
    * nice way after the simulation has completed.  If we are dealing with
    * a very large number of visits (ex: simulation is run to model many
    * days of execution in a very large store) then storing the visits
    * in this way would not be feasible.
    */
    private final ArrayList<OutPatient> visits;
    private final int patientsCount;
    private OutPatient currentPatient;
    private final PatientConsultant consultant;
    private final Random r;
    int lineCount;
    long started;
    AtomicLong serialNumber;

    public QueueProcessor() {
        this(0);
    }

    public QueueProcessor(int n) {
        FEL = new PriorityQueue<>();
        patients = new LinkedList<>();  // The visits waiting to be treated are in a simple queue (FIFO).
        patientsCount = n;
        visits = new ArrayList<>();

        consultant = new PatientConsultant();
        r = new Random((new Date()).getTime());
        serialNumber = new AtomicLong(0);
        started = System.currentTimeMillis();
    }

    //add to queue based on number of Processors[Doctors]
    public long add(long clock) {
        long id = serialNumber.incrementAndGet();
        long time = System.currentTimeMillis() - started;
        ArrivalEvent event = new ArrivalEvent(time);
        currentPatient = new OutPatient(id, event.getTime());
        currentPatient.setServiceTime(serviceTime());
        if (!consultant.isConsulting()) {
            currentPatient.setStartServiceTime(clock);
            consultant.addPatient(currentPatient);
            FEL.add(new CompletionEvent(clock + currentPatient.getServiceTime()));
        } else {
            patients.add(currentPatient);
        }
        if (lineCount < patientsCount) {
            FEL.add(new ArrivalEvent(clock + deltaCustomer()));
        }
        System.out.println("New patient pushed to waiting list.");
        System.out.println(String.format("There are %d other people ahead of you.",(id-visits.size())));
        System.out.println("Queue number: "+id+". Wait time: "+(((id-visits.size())*3000)/1000)+"s\n");
        return id;
    }

    /**
    * Ensure next patient is notified of the completes of consultationn
    * with current patient
    */
    public void complete(long clock) {
        currentPatient = consultant.removePatient();
        currentPatient.setEndServiceTime(clock);
        currentPatient.setWaitTime(currentPatient.getArrivalTime() - currentPatient.getServiceTime());
        currentPatient.setInSystemTime(clock - currentPatient.getArrivalTime());
        visits.add(currentPatient);
        if (!patients.isEmpty()) {
            currentPatient = patients.remove();
            currentPatient.setStartServiceTime(clock);
            consultant.addPatient(currentPatient);
            FEL.add(new CompletionEvent(clock + currentPatient.getServiceTime()));
        }
    }

    /**
     * Iterate as long as there are events left in the FEL. Since events are
     * both arrival events and completion events, we must distinguish between
     * the two and take different actions accordingly.
     *
     * @throws java.lang.ClassNotFoundException
     * @see QueueEvent, ArrivalEvent and CompletionEvent.
     */
    public void process() throws ClassNotFoundException {
        FEL.add(new ArrivalEvent(System.currentTimeMillis() - started));
        // Initialize FEL with first arrival
        // Simulation should run until no more events are left
        while (!FEL.isEmpty()) {
            QueueEvent e = FEL.remove();
            long clock = e.getTime();
            if (Class.forName("com.ocularminds.ocare.qs.ArrivalEvent").isInstance(e)) {
                lineCount++;
                add(clock);
            } else {
                // Event is a CompletionEvent
                complete(clock);
            }
        }
    }

    // Go through the list of visits and output their information (as well as
    // some other statistics).  Note that alternatively much of this output
    // could be done as the simulation is executing.
    public void report() {
        System.out.println("Customer  Time Since  Arrival  Service  Time Service  Time Customer  Time Service   Time Spent  Idle Server");
        System.out.println("   Id        Last      Time     Time       Begins         Waits         Ends          in Sys       Time");
        System.out.println("--------  ----------  -------  -------  ------------  -------------  ------------   ----------  -----------");
        long oldA = 0;
        long oldT = 0;
        long arrTot = 0;
        long servTot = 0;
        long waitTot = 0;
        long sysTot = 0;
        long idleTot = 0;
        long numWait = 0;
        long totalTime = 0;
        for (int i = 0; i < visits.size(); i++) {
            currentPatient = visits.get(i);
            System.out.print("   " + currentPatient.getNumber() + "\t");
            if (i == 0) {
                System.out.print("      " + 0 + "\t\t");
            } else {
                long deltaA = currentPatient.getArrivalTime() - oldA;
                System.out.print("      " + deltaA + "\t\t");
                arrTot += deltaA;
            }
            System.out.print(currentPatient.getArrivalTime() + "\t");
            System.out.print("  " + currentPatient.getServiceTime() + "\t");
            servTot += currentPatient.getServiceTime();
            System.out.print("    " + currentPatient.getStartServiceTime() + "\t\t");
            long currWait = currentPatient.getStartServiceTime() - currentPatient.getArrivalTime();
            System.out.print("   " + currWait + "\t");
            waitTot += currWait;
            if (currWait > 0) {
                numWait++;
            }
            System.out.print("\t " + currentPatient.getEndServiceTime() + "\t");
            System.out.print("\t" + (currentPatient.getEndServiceTime() - currentPatient.getArrivalTime()) + "\t");
            sysTot += currentPatient.getEndServiceTime() - currentPatient.getArrivalTime();
            if (i == 0) {
                System.out.print("     " + 0 + "\t");
            } else {
                long idleT = currentPatient.getStartServiceTime() - oldT;
                System.out.print("     " + idleT + " ");
                idleTot += idleT;
            }
            oldT = currentPatient.getEndServiceTime();
            oldA = currentPatient.getArrivalTime();
            if (i == (visits.size() - 1)) {
                totalTime = currentPatient.getEndServiceTime();
            }
            System.out.println();
        }
        System.out.println();
        System.out.println(String.format("%18s %12f", "Average Wait: ", (waitTot / ((float) patientsCount))));
        System.out.println(String.format("%18s %12f", "P(wait): ", (numWait / ((float) patientsCount))));
        System.out.println(String.format("%18s %12f", "Frac. Idle: ", (idleTot / ((float) totalTime))));
        System.out.println(String.format("%18s %12f", "Ave. Service: ", (servTot / ((float) patientsCount))));
        System.out.println(String.format("%18s %12f", "Ave. Interarrival:", (arrTot / ((float) (patientsCount - 1)))));
        System.out.println(String.format("%18s %12f", "Ave. Waiter Wait: ", (waitTot / ((float) numWait))));
        System.out.println(String.format("%18s %12f", "Ave. in System: ", (sysTot / ((float) patientsCount))));
    }

    /**
     * The methods here uses distributions that changes the inter-arrival
     * distribution to exponential and it changes the service distribution to
     * normal. Method to provide the inter-arrival time distribution
     * @return
     */
    public int deltaCustomer() {
        return (r.nextInt(9999) + 1000);
    }

    // Method to provide the service time distribution
    public int serviceTime() {
        double zeroOne = r.nextDouble();
        if (zeroOne <= 0.1) {
            return 1000;
        } else if (zeroOne <= 0.30) {
            return 2000;
        } else if (zeroOne <= 0.60) {
            return 3000;
        } else if (zeroOne <= 0.85) {
            return 4000;
        } else if (zeroOne <= 0.95) {
            return 5000;
        } else {
            return 6000;
        }
    }
}
