package BattleshipRMI;
/*
 * Server.java
 *
 * Version:
 *     1
 *
 */

/**
 * This class acts as a server class using RMI.
 *
 * @author Ishika Prasad
 */

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {
    static GameBoardRemoteObject obj=new GameBoardRemoteObject();

    /**
     * Constructor
     */
    public Server(){}

    /**
     * The main method
     * @param args Commandline Arguments
     */
    public static void main(String args[])
    {
        try{

            BattleShipInterface stub=(BattleShipInterface) UnicastRemoteObject.exportObject(obj, 0);
            //port number
            Registry registry= LocateRegistry.createRegistry(Integer.valueOf(args[0]));
            registry.bind("Battleship", stub);
            System.out.println("Game Begins");
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}
