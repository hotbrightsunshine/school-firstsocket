package it.parisio;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server 
{
    static  ServerSocket            serversocket;
    static  Socket                  socket;
    static  BufferedReader          reader;
    static  DataOutputStream        outputStream;
    static  String                  message;

    static  ArrayList<Thread>       threadlist;
    static ArrayList<Socket>        threadlist;

    public static void launch() throws Exception {
        serversocket = new ServerSocket(2022);
        System.out.println("Server started.");
        while(true){
            socket = serversocket.accept();
            SlayThread s = ;
            Thread t = new Thread(new SlayThread(socket, Server));
            t.start();
            threadlist.add(t);
            // arraylist thread per spegnerli 
        }
    }

    public static void stop() throws Exception {
        for (Thread thread : threadlist) {
            thread.join();
        }
    }

    public static void main( String[] args ) throws Exception {
        launch();
    }
}
