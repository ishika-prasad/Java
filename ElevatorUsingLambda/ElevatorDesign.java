/*
 * ElevatorDesign.java
 *
 * Version:
 *     1
 *
 */

/**
 * This program is to design an elevator
 * for a given requirement using lambda expression
 *
 * @author  Ishika Prasad
 *
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


/**
 * This class includes attributes
 * of the floor
 */
class Floors{
    ArrayList<Person> passengerQueue = new ArrayList<>();
    boolean requestFlag = false;
    int currFloor;
    public Floors(int floor){
        this.currFloor = floor;
    }
}

/**
 * This abstract class is for initializing
 * the floors
 */
abstract class buildingElevator extends Thread{
    static Floors[] getFloors = new Floors[5];
    public buildingElevator(){
        for(int i = 0;i<5;i++){
            getFloors[i] = new Floors(i);
            //System.out.println(getFloors[i]);
        }
    }
}

interface waited{
    boolean inWait(Floors floor, Person p);
}

/**
 * This class has the property of Person.
 */
class Person extends buildingElevator{

    int weight = 0;
    int fromFloor = 0;
    int toFloor = 0;
    boolean waitedOnFloor = false;
    Random rand = new Random();
    static int count = 0;
    //Floors[] getFloor;
    String name;
    static int flag = 0;
    static Object o;
    static int assignedFloor[] = new int[5];
    public Person(String name, Object o){
        this.name = name;
        this.o = o;
        //this.getFloor = getFloor;
    }

    /**
     * This method is used to set the person
     * attributes.
     */
    public void setPassengerAttributes(){
        synchronized (o) {
            if (!waitedOnFloor) {
                //System.out.println(getFloors[fromFloor].passengerQueue);
                getFloors[fromFloor].passengerQueue.add(this);
                getFloors[fromFloor].requestFlag = true;
                this.weight = rand.nextInt(150) + 100;

                this.toFloor = rand.nextInt(5);
                if (count < 5) {
                    while (true) {
                        if (assignedFloor[this.toFloor] == 0) {
                            assignedFloor[this.toFloor]++;
                            break;
                        }
                        this.toFloor = rand.nextInt(5);
                    }
                    count++;
                }
            } else {
                this.fromFloor = this.toFloor;
                this.toFloor = 0;
            }
        }
    }

    /**
     * This method is for 3 seconds wait once it reaches the floor
     * @param floor person's requested floor
     * @throws InterruptedException
     */
    public boolean inWait(Floors floor) {
        try {
            this.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        setPassengerAttributes();
        waitedOnFloor = true;
        floor.passengerQueue.add(this);
        floor.requestFlag = true;
        return waitedOnFloor;

    }

    /**
     * This is Person's run method in which
     * person thread will be assigned of attributes and
     * printing the attributes accordingly.
     */
    public void run(){
        if(flag == 0){
            for(int i = 0; i <5 ; i++){
                assignedFloor[i] = 0;
            }
            flag = 1;
        }
        setPassengerAttributes();
        System.out.println(this.name+" has weight "+ this.weight + "and will " +
                "go from floor "+ (this.fromFloor+1) +" to floor "+ (this.toFloor+1));
    }

}


/**
 * This class is for elevator attributes and
 * operations.
 */
public class ElevatorDesign extends buildingElevator{
    final int capacity = 700;
    static int currentWeight = 0;

    ArrayList<Person> passengerList = new ArrayList<>();
    static int passengerCount = 0;
    static int currentFloor = 0;
    static int directionflag = 1;

    /**
     * This method consist of the person and elevator
     * operation and checking all the parameter.
     * @param currentFloor
     * @throws InterruptedException
     */
    public void personOperations(int currentFloor) {
        synchronized (Person.o) {
            passengerList.forEach(j -> {
                if (j.toFloor == currentFloor) {
                    System.out.println(j.name + " on floor " + (currentFloor + 1));
                    currentWeight -= j.weight;
                    passengerList.remove(j);
                    j.inWait(getFloors[currentFloor]);
                    getFloors[currentFloor].passengerQueue.add(j);
                }
            });
        }
        while (getFloors[currentFloor].passengerQueue.size() != 0) {
            Person person =
                    getFloors[currentFloor].passengerQueue.get(0);
            if (person.fromFloor == currentFloor) {
                if (capacity > currentWeight + person.weight) {
                    currentWeight += person.weight;
                    passengerList.add(person);

                    System.out.println(person.name + " in elevator on " +
                            "floor " + (currentFloor+1));
                    getFloors[person.toFloor].requestFlag = true;
                    getFloors[currentFloor].passengerQueue.remove(person);
                }
                else {
                    break;
                }
            }
        }
    }

    /**
     * This method gives the summary of elevator and person. It gives summary
     * of even numbered people statistics.
     * @param p     Person[]    Person array with attributes for all person
     *              threads
     */
    public void elevatorAndPersonSummary(Person[] p){
        List<Integer> people= Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        people.stream().filter(n->(n+1)%2 ==0).forEach(x->{
            System.out.println("person"+p[x].name);
            System.out.println("Weight: "+p[x].weight);
            System.out.println("Status: "+passengerList.contains(p[x]));
            System.out.println("Weight Contribution: "+((p[x].weight/currentWeight)*100));});

    }
    /**
     * This method is for set thr requesFlag
     * @param i current floor
     */
    public void requested(int i){


        personOperations(i);
        getFloors[i].requestFlag = false;

    }

    /**
     * This is elevator's run method in which the threads
     * will start to get the floor and check the passenger
     * list
     */
    public void run(){
        while(true) {
            if (directionflag == 1) {
                for (int i = 0; i < 5; i++) {
                    currentFloor = i;
                    if (getFloors[i].requestFlag) {

                        requested(i);
                        try {
                            this.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                    try {
                        this.sleep(500);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                directionflag = 0;
            } else {
                for (int i = 4; i >= 0; i--) {
                    currentFloor = i;
                    if (getFloors[i].requestFlag) {
                        requested(i);
                    }
                    try {
                        this.sleep(500);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                directionflag = 1;
            }
        }
    }

    /**
     * The main method
     * @param args no args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        ElevatorDesign elevator = new ElevatorDesign();
        Object o = new Object();
        Person[] people = new Person[10];

        for(int i = 0; i < 10 ; i++) {
            people[i] = new Person("person"+(i+1),o);
            people[i].start();
        }
        elevator.start();
        elevator.elevatorAndPersonSummary(people);
        elevator.join();
    }
}