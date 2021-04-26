/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Message.Message;
import main.Game;

import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mert
 */
public class Client {

    Game game;

    public static Socket socket;

    //verileri almak için gerekli nesne
    public static ObjectInputStream sInput;
    //verileri göndermek için gerekli nesne
    public static ObjectOutputStream sOutput;
    //serverı dinleme thredi 
    public static Listen listenMe;

    public Client(Game game){
        this.game = game;
        listenMe = new Listen(this);
    }
    
    public static void Start(String ip, int port) {
        try {
            // Client Soket nesnesi
            Client.socket = new Socket(ip, port);
            System.out.println("Servera bağlandı");
            // input stream
            Client.sInput = new ObjectInputStream(Client.socket.getInputStream());
            // output stream
            Client.sOutput = new ObjectOutputStream(Client.socket.getOutputStream());
            listenMe.start();
            //ilk mesaj olarak isim gönderiyorum
            Message msg = new Message(Message.Message_Type.Name);
            
            Client.Send(msg);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void Stop() {
           try {
               if (Client.socket != null) {
                   Client.listenMe.stop();
                   Client.socket.close();
                   Client.sOutput.flush();
                   Client.sOutput.close();
                   Client.sInput.close();
               }
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
        //soket bağlı olduğu sürece dön
        while (Client.socket.isConnected()) {
            
            try {
                //mesaj gelmesini bloking olarak dinyelen komut
                Message received = (Message) (Client.sInput.readObject());
                //mesaj gelirse bu satıra geçer
                //mesaj tipine göre yapılacak işlemi ayır.
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
