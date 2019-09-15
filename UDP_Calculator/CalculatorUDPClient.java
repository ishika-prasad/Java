/*
 * CalculatorUDPclient.java
 *
 * Version:
 *     1
 *
 */

/**
 * This program implement a client which will print solution
 * of the equation each time a client connects to the server.
 *
 * @author  Ishika Prasad
 *
 */
import java.net.*;
import java.util.*;
public class CalculatorUDPClient {

    String serverHostName = "localhost";
    int    serverPort = 8787;

    /**
     * Parse the commandlind arguments and sets variables.
     */
    public void parseArgs(String args[]) {

        for (int i = 0; i < args.length; i++) {

            int count = 0;
            if (args[i].equals("-server"))
                serverHostName = args[++i];
            else if (args[i].equals("-port"))
                serverPort = new Integer(args[++i]).intValue();
        }
    }

    /**
     * This method connects to the server and send the
     * equation needs to be calculated.
     */
    public void getCalculatorOperation() {

        DatagramSocket socket = null;
        DatagramPacket packet = null;
        Scanner sc = new Scanner(System.in);
        try {
            //for send data
            byte buf[] = new byte[512];
            //for receive data
            byte buf1[] = new byte[512];

            InetAddress aInetAddress = InetAddress.getByName(serverHostName);
            socket = new DatagramSocket();
            System.out.println("Enter and send the equation to Server: ");
            String equation = sc.nextLine();
            buf = equation.getBytes();
            packet = new DatagramPacket(buf, buf.length, aInetAddress, serverPort);
            socket.send(packet);
            DatagramPacket dp = new DatagramPacket(buf1, buf1.length);
            socket.receive(dp);
            System.out.println("Received from server: \n" + "Answer for equation is " + new String(dp.getData()).trim());
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }finally {
            //closing the socket connection
            socket.close();
        }
    }

    /**
     * The main program.
     * @param argv -server, -port
     */
    public static void main(String argv[]) {
        CalculatorUDPClient calculatorUDPClient = new CalculatorUDPClient();
        calculatorUDPClient.parseArgs(argv);
        calculatorUDPClient.getCalculatorOperation();

    }
}