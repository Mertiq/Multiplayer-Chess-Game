package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import Message.Message;
import main.Game;

public class Client {

    Game game;

    public static Socket socket;


    public static ObjectInputStream sInput;
    public static ObjectOutputStream sOutput;
    public static Listen listenMe;

    public Client(Game game){
        this.game = game;
        listenMe = new Listen(this);
    }
    
    public static void Start(String ip, int port) {
        try {
            Client.socket = new Socket(ip, port);
            Client.sInput = new ObjectInputStream(Client.socket.getInputStream());
            Client.sOutput = new ObjectOutputStream(Client.socket.getOutputStream());
            listenMe.start();
            Message msg = new Message(Message.Message_Type.Name);
            Client.Send(msg);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void Send(Message msg) {
        try {
            Client.sOutput.writeObject(msg);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}
class Listen extends Thread {

    Client client;

    public Listen(Client c){
        client=c;
    }

    public void run() {

        while (Client.socket.isConnected()) {
            
            try {
                Message received = (Message) (Client.sInput.readObject());

                switch (received.type) {
                    case SideChoose:
                        client.game.isSideWhite = (boolean) received.content;
                        break;
                    case Move:
                        client.game.MoveServer((ArrayList<Object>) received.content,client.game);
                        break;

                }
            } catch (IOException ex) {
                Logger.getLogger(Listen.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Listen.class.getName()).log(Level.SEVERE, null, ex);
            }

            
        }

    }
}
