package it.parisio;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server 
{
    ServerSocket            serversocket;
    Socket                  socket;
    BufferedReader          reader;
    DataOutputStream        outputStream;
    String                  message;

    ArrayList<Thread>       threadlist = new ArrayList<>();
    ArrayList<Socket>       socketlist = new ArrayList<>();
    ArrayList<SlayThread>   slays = new ArrayList<>();

    public void launch() {
        try {
            serversocket = new ServerSocket(2022);
        } catch (Exception e) {
            System.out.println("Unable to start.");
        }

        System.out.println("Server started.");
        while(true){
            SlayThread slay;
            try {
                socket = serversocket.accept();
                slay = new SlayThread(socket, this);
                Thread t = new Thread(slay);
                t.start();
    
                threadlist.add(t);
                slays.add(slay);
            } catch (Exception e){
                System.out.println("Unable to open a new socket.");
                break;
            };
        }
    }

    public void slay() throws Exception {
        for (SlayThread slay : slays) {
            slay.ad_stop();
        }
        serversocket.close();
        System.out.println("Server slayed.");
    }

    public void main( String[] args ) throws Exception {
        launch();
    }
}
