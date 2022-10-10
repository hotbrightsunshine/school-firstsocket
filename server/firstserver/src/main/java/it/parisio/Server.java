package it.parisio;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server 
{
    static  ServerSocket        serversocket;
    static  Socket              socket;
    static  BufferedReader      reader;
    static  DataOutputStream    outputStream;
    static  String              message;

    public static void launch() throws Exception {
        serversocket = new ServerSocket(2022);
        System.out.println("Server started.");
        while(true){
            socket = serversocket.accept();
            Thread t = new Thread(new SlayThread(socket));
            t.start();
        }
    }

    public static void main( String[] args ) throws Exception {
        launch();
    }
}
