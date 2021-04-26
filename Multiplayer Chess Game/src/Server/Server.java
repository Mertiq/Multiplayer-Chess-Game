/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import Message.Message;

/**
 *
 * @author Mert
 */
public class Server {
    
    public static ServerSocket serverSocket;
    public static int IdClient = 0;
    public static int port = 0;
    public static ServerThread runThread;
    public static ArrayList<SClient> Clients = new ArrayList<>();
    
    public static Semaphore pairTwo = new Semaphore(1, true);
     public static void Start(int openport) {
        try {
            Server.port = openport;
            Server.serverSocket = new ServerSocket(Server.port);

            Server.runThread = new ServerThread();
            Server.runThread.start();

        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public static void Send(SClient cl, Message msg) {

        try {
            cl.sOutput.writeObject(msg);
        } catch (IOException ex) {
            Logger.getLogger(SClient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
}

class ServerThread extends Thread {

    public void run() {
        //server kapanana kadar dinle
        while (!Server.serverSocket.isClosed()) {
            try {
                System.out.println("Client Bekleniyor...");
                
                Socket clientSocket = Server.serverSocket.accept();

                System.out.println("Client Geldi...");
                
                SClient nclient = new SClient(clientSocket, Server.IdClient);

                Server.IdClient++;
                
                Server.Clients.add(nclient);
                
                nclient.listenThread.start();

            } catch (IOException ex) {
                Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
