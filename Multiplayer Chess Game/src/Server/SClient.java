package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import Message.Message;

public class SClient {

    public boolean isWhite;
    int id;
    public String name = "NoName";
    Socket soket;
    ObjectOutputStream sOutput;
    ObjectInputStream sInput;
    Listen listenThread;
    PairingThread pairThread;
    SClient rival;
    public boolean paired = false;
    
    public SClient(Socket gelenSoket, int id) {
        this.soket = gelenSoket;
        this.id = id;
        try {
            this.sOutput = new ObjectOutputStream(this.soket.getOutputStream());
            this.sInput = new ObjectInputStream(this.soket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(SClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.listenThread = new Listen(this);
        this.pairThread = new PairingThread(this);

    }

}

class Listen extends Thread {

        SClient TheClient;

        Listen(SClient TheClient) {
            this.TheClient = TheClient;
        }

        public void run() {

            while (TheClient.soket.isConnected()) {
                try {
                    Message received = (Message) (TheClient.sInput.readObject());

                    switch (received.type) {
                        case Name:
                            TheClient.pairThread.start();
                            break;
                        case SideChoose:
                            TheClient.isWhite = (boolean) received.content;
                            break;
                        case Move:
                            Server.Send(TheClient.rival,received);
                            break;
                    }

                } catch (IOException ex) {
                    Logger.getLogger(SClient.class.getName()).log(Level.SEVERE, null, ex);
                    Server.Clients.remove(TheClient);

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(SClient.class.getName()).log(Level.SEVERE, null, ex);
                    Server.Clients.remove(TheClient);
                }
            }

        }
    }

    class PairingThread extends Thread {

        SClient TheClient;

        PairingThread(SClient TheClient) {
            this.TheClient = TheClient;
        }

        public void run() {
            while (TheClient.soket.isConnected() && TheClient.paired == false) {
                try {

                    Server.pairTwo.acquire(1);

                    if (!TheClient.paired) {
                        SClient crival = null;
                        while (crival == null && TheClient.soket.isConnected()) {
                            for (SClient clnt : Server.Clients) {
                                if (TheClient != clnt && clnt.rival == null) {
                                    crival = clnt;
                                    crival.paired = true;
                                    crival.rival = TheClient;
                                    TheClient.rival = crival;
                                    TheClient.paired = true;
                                    crival.isWhite = !TheClient.isWhite;
                                    Message msg = new Message(Message.Message_Type.SideChoose);
                                    msg.content = !TheClient.isWhite;
                                    Server.Send(crival,msg);
                                    break;
                                }
                            }
                            sleep(1000);
                        }
                        Message msg1 = new Message(Message.Message_Type.OpponentConnected);
                        msg1.content = TheClient.name;
                        Server.Send(TheClient.rival, msg1);

                        Message msg2 = new Message(Message.Message_Type.OpponentConnected);
                        msg2.content = TheClient.rival.name;
                        Server.Send(TheClient, msg2);
                    }
                    Server.pairTwo.release(1);
                } catch (InterruptedException ex) {
                    Logger.getLogger(PairingThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
