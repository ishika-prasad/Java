/*
 * CalculatorUDPServer.java
 *
 * Version:
 *     1
 *
 */

/**
 * This program implements a server which will return solution
 * of the equation each time a client connects to the server.
 *
 * @author  Ishika Prasad
 *
 */

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *This interface contains method for operation to
 * be performed on the two integers.
 */
interface OperationFunctionalInterface{
    int operation(int a, int b);
}
    
/**
 * This interface contains performOperation which perform
 * the operation on integers and return the result based on
 * the operation.
 * The method helpMessage exit with
 * code 1: incorrect number of arguments
 * code 2: if number is not valid
 * code 3: if operator is not valid
 */
interface CalculatorInterface {
    int performOperation(OperationFunctionalInterface ofi, int num1, int num2);

    static void helpMessage(int exitCode) {
        System.out.println("Usage: <num> <operation> <num>");
        System.exit(exitCode);
    }
}


/**
 * This class is for udp server which calculates
 * calculator operation and send to client.
 */
public class CalculatorUDPServer extends Thread {

    DatagramSocket socket;
    static int port = 8787;

    //default constructor
    public CalculatorUDPServer() {
    }

    /**
     * Parametrized constructor with argument as port number
     * which will open a socket connection.
     * @param port port number
     */
    public CalculatorUDPServer(int port) {
        try {
            socket = new DatagramSocket(port);
            System.out.println("UDP server listening on port: " + socket.getLocalPort());
        }catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Parse the commandline arguments and set port.
     */
    public void parseArgs(String args[]) {
        for(int i = 0; i < args.length; i++) {
            if(args[i].equals("-port")) {
                port = new Integer(args[++i]).intValue();
                new CalculatorUDPServer(port).start();
            }
        }
    }

    /**
     * This is run method which accepts the equation from client
     * and send the calculated equation result to the client.
     */
    public void run() {
        byte[] buf = new byte[512]; //for send data
        byte[] buf1 = new byte[512]; //for receive data
        try {
            while(true) {
                //receive the equation
                DatagramPacket packet = new DatagramPacket(buf1, buf1.length);
                socket.receive(packet);
                String recData = new String(packet.getData());
                recData = recData.trim();
                System.out.println("Given equation is " + recData);
                String[] data = recData.split("\\s+");
                if(data.length != 3) {
                    System.out.println("Incorrect number of arguments ");
                    CalculatorInterface.helpMessage(1);
                }
                int calculation = calculatorOperation(data[0], data[1], data[2]);
                String calculate = Integer.toString(calculation);
                InetAddress address = packet.getAddress();
                port = packet.getPort();
                buf = calculate.getBytes();
                packet = new DatagramPacket(buf, buf.length, address, port);
                System.out.println("Sending to port: " + port + "\n");
                socket.send(packet);
            }
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        } finally {
            //closing the socket connection
            socket.close();
        }
    }

    /**
     * This method is for calculating the equation based on operand and
     * given operator.
     * @param Operand1 first digit
     * @param operator given operator (+, -, *, /)
     * @param Operand2 second digit
     * @return calculation of the given equation
     */
    public int calculatorOperation(String Operand1, String operator, String Operand2) {
        int calculation = 0;

        //if number is not valid then exit code of 2
        try {
            Integer.parseInt(Operand1);
            Integer.parseInt(Operand2);
        }catch (Exception e) {
            System.out.println("Required valid number ");
            CalculatorInterface.helpMessage(2);
        }

        //if operator is not valid then exit code of 3
        if (!(operator.equals("+") || (operator.equals("-") || (operator.equals("*") || (operator.equals("/")))))) {
            System.out.println("Operator is not valid ");
            CalculatorInterface.helpMessage(3);
        }

        //perform calculator operation
        switch(operator) {
            //perform addition operation
            case "+" :
                OperationFunctionalInterface addition = (a, b) -> (a + b);
                CalculatorInterface performAddition = (additionOp, a, b) -> (addition.operation(a, b));
                calculation = performAddition.performOperation(addition, Integer.parseInt(Operand1),
                        Integer.parseInt(Operand2));
                System.out.println("Sending answer to Client: " + calculation);
                break;

            //perform subtraction operation
            case "-" :
                OperationFunctionalInterface subtraction = (a, b) -> (a - b);
                CalculatorInterface performSubtraction = (subtractionOp, a, b) -> (subtraction.operation(a, b));
                calculation = performSubtraction.performOperation(subtraction, Integer.parseInt(Operand1),
                        Integer.parseInt(Operand2));
                System.out.println("Sending answer to Client: " + calculation);
                break;

            //perform multiplication operation
            case "*" :
                OperationFunctionalInterface multiplication = (a, b) -> (a * b);
                CalculatorInterface performMultiplication = (multiplicationOp, a, b) -> (multiplication.operation(a, b));
                calculation = performMultiplication.performOperation(multiplication, Integer.parseInt(Operand1),
                        Integer.parseInt(Operand2));
                System.out.println("Sending answer to Client: " + calculation);
                break;

            //perform division operation
            case "/" :
                OperationFunctionalInterface division = (a, b) -> (a / b);
                CalculatorInterface performDivision = (divisionOp, a, b) -> (division.operation(a, b));

                calculation = performDivision.performOperation(division, Integer.parseInt(Operand1),
                        Integer.parseInt(Operand2));
                System.out.println("Sending answer to Client: " + calculation);
                break;
        }
        return calculation;
    }

    /**
     * The main program
     * @param argv -port
     */
    public static void main(String argv[]) {
        // if argument is null, then default port will be executed
        if (argv.length == 0)
            new CalculatorUDPServer(port).start();
        else
            new CalculatorUDPServer().parseArgs(argv);
    }
}
