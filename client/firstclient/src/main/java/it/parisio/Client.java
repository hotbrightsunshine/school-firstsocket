package it.parisio;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class Client 
{
    static  Socket              socket;
    static  DataOutputStream    outputStream;
    static  BufferedReader      reader;
    static  String              message;
    static  Listener            listener;
    static  Thread              listenerThread;

    public static boolean sendMessage (DataOutputStream outputStream, String message) 
    {    
        try {
            outputStream.writeBytes(message);
            return true;
        } catch (Exception e) { return false; }
    }

    public static void slay (){
        try {
            socket.close();
        } catch (IOException e) {}
        synchronized (System.out) {
            System.out.println("Client slayed.");
        }
    }

    public static void listen (){
        while (!socket.isClosed()){
            // Ascolta di continuo
            String msg = null;
            try {
                msg = reader.readLine();
            } catch (IOException e) { }

            if (msg == null){
                continue;
            } else if ( msg.equals("STOP")) {
                slay();
                System.exit(0);
                //try {
                //    listenerThread.join();
                //} catch (InterruptedException e) { }
                //return;
            } else {
                synchronized (System.out) {
                    System.out.println("# " + msg);
                }
            }
        }   
    }

    public static void main( String[] args )
    {

        System.out.println("! Client started.");

        // Creating a connection.
        try {
            socket = new Socket("localhost", 2022);
            outputStream = new DataOutputStream(socket.getOutputStream());
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            listener = new Listener(outputStream, socket);
            listenerThread = new Thread(listener);

            synchronized (System.out) {
                System.out.println("Connection opened.");
            }
            listenerThread.start();
            
            listen();

            

        } catch (Exception e) {
            synchronized (System.out) {
                System.out.println("Unable to connect or send a message.");
            };
            return;
        }
    }
}
