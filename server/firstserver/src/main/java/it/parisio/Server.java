package it.parisio;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server 
{
    static  ServerSocket        serversocket;
    static  Socket              socket;
    static  BufferedReader      reader;
    static  DataOutputStream    outputStream;
    static  String              message;

    public static Socket getSocket () 
    {
        try {
            serversocket = new ServerSocket(2022);
        } catch (Exception e) { }
        
        try {
            return serversocket.accept();
        } catch (Exception e) { return null; }
    }

    public static void main( String[] args )
    {
        while(true){ // ugly but works
            socket = getSocket();

            try {
                serversocket.close();
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                outputStream = new DataOutputStream(socket.getOutputStream());
            } catch (IOException e) { }

            System.out.println("The connection has been successfully created.");

            try {
                message = reader.readLine();
                System.out.println("Ho ricevuto questo messaggio: " + message);
            } catch (IOException e) { }



            try {
                outputStream.writeBytes(message.toUpperCase() + "!!!\n");
                System.out.println("Reply message sent. ");
            } catch (Exception e) { }

            try {
                socket.close();
            } catch (IOException e) { }
        }
    }
}
