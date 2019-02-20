/*
 * GravityCalculator.java
 *
 * Version:
 *     1
 *
 */

/**
 * This program calculates the distance and velocity an object
 * travels in the downwards direction given some length of time
 * based on gravity of Earth and Mars.
 *
 *
 * @author  Ishika Prasad
 * @author  Pavitra Sapaliga
 *
 */

public class GravityCalculator {

    static double gravity, distance, velocity, time;

    /**
     * This method is used to calculate and print the
     * distance and velocity on Earth and Mars.
     *
     */
    public static void calculation() {
        distance = (gravity/2)* time*time;
        velocity = gravity * time;
        System.out.print("The object's position after " + time + " seconds is ");
        System.out.printf("%.2f", distance);
        System.out.println(" m from starting position");
        System.out.print("Current velocity is ");
        System.out.printf("%.2f", velocity);
        System.out.println(" m/s");
    }

    /**
     * The main program.
     * @param args command line input
     *             args[0] can be planet either "earth" or "mars"
     *             args[1] is the time duration
     *
     */
    public static void main(String[] args) {

        try {
            time = Double.parseDouble(args[1]);
            if (args.length == 2) {
                if (args[0].equals("earth")) {
                    gravity = 9.81;
                    calculation();
                } else if (args[0].equals("mars")) {
                    gravity = 3.711;
                    calculation();
                } else {
                    System.out.println("The planet name can't be other than earth or mars");
                }
            } else {
                System.out.println("The argument length should be two: planet and time duration");
            }
        } catch (Exception e) {
            System.out.println("Time duration should be Integer, Float or Double");
        }
    }
}
