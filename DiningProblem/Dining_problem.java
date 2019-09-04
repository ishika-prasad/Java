/*
 * Dining_problem.java
 *
 * Version:
 *     1
 *
 */

/**
 * This program is used to solve the dining
 * philospher's problem using semaphore
 *
 * @author  Ishika Prasad
 */
import java.util.Random;
import java.util.concurrent.Semaphore;


public class Dining_problem {
    static Philosopher[] p = new Philosopher[5];
    static Semaphore mutex = new Semaphore(1);

    public static class Philosopher extends Thread {
        protected static Random random = new Random();

        final int me;
        static String flag;
        final Semaphore self;
        static int count = 0;

        Philosopher(int me) {
            this.me = me;
            self = new Semaphore(0);
            flag = "Thinks";
        }

        /**
         * This method is to choose left chopstick
         * on the basis of where the diner is sitting.
         *
         * @return left chopstick of diner
         */
        public Philosopher leftChopstick() {
            if(me == 0) {
                return p[4];
            }
            else {
                return p[me - 1];
            }
        }

        /**
         * This method is to choose right chopstick
         * on the basis of where the diner is sitting.
         *
         * @return right chopstick of diner
         */
        public Philosopher rightChopstick() {
            if(me == 4) {
                return p[0];
            }
            else {
                return p[me + 1];
            }
        }

        /**
         * This method is requested to eat and acquire the mutex
         * and flag is set to "trying to eat"
         *
         * @throws InterruptedException
         */
        public void thinks() throws InterruptedException {
            try {
                Thread.sleep((long)(random.nextFloat()*3000));
            } catch(Exception e) {
                e.printStackTrace();
            }
            mutex.acquire();
            flag = "Trying to eat";
        }

        /**
         * This method checks if this chopstick
         * is free then release the mutex lock and acquire
         * self lock. The flag is set to "Eats".
         *
         * @throws InterruptedException
         */
        public void trying_to_eat() throws InterruptedException {
            checkChopstick(this);
            mutex.release();
            self.acquire();
            flag = "Eats";
        }

        /**
         * This method checks acquire the mutex lock
         * and eats when left chopstick and right chopstick
         * is free and release the mutex.
         *
         * @throws InterruptedException
         */
        public void eats() throws InterruptedException {
            try {
                Thread.sleep((long)(random.nextFloat()*3000));
            } catch(Exception e) {
                e.printStackTrace();
            }
            mutex.acquire();
            flag = "Thinks";
            checkChopstick(leftChopstick());
            checkChopstick(rightChopstick());
            mutex.release();
        }

        /**
         * This method checks if the left chopstick
         * and right chopstick is free and set flag
         * to Eats and release the self lock.
         * @param p left chopstick or right chopstick
         */
        private static void checkChopstick(Philosopher p) {
            if (p.flag == "Trying to eat" &&
                    p.leftChopstick().flag != "Eats" &&
                    p.rightChopstick().flag != "Eats") {
                p.flag = "Eats";
                p.self.release();
            }
        }

        /**
         * This method print the threads and counter.
         * According to the flag, it will go the
         * required method.
         */
        public void run() {
            Philosopher ph = new Philosopher(me);
            try {

                while (true) {
                    System.out.println(me + " " + flag);
                    count++;
                    System.out.println("Count is " + count);
                    switch(flag) {
                        case "Thinks":
                            ph.thinks();
                            break;

                        case "Trying to eat":
                            ph.trying_to_eat();
                            break;

                        case "Eats":
                            ph.eats();
                            break;
                    }
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * The main program
     * @param args no args
     */
    public static void main(String[] args) {

        p[0] = new Philosopher(0);
        for (int i = 1; i < 5; i++) {
            p[i] = new Philosopher(i);
        }
        for(int i = 0; i < 5; i++) {
            p[i].start();
        }
    }
}
