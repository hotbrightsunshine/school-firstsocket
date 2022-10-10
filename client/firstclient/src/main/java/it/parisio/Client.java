package it.parisio;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class Client 
{
    static  Socket              socket;
    static  DataOutputStream    outputStream;
    static  BufferedReader      reader;
    static  String              message;

    public static boolean sendMessage (DataOutputStream outputStream, String message) 
    {    
        try {
            outputStream.writeBytes(message);
            return true;
        } catch (Exception e) { return false; }
    }

    public static void main( String[] args )
    {

        System.out.println("! Client started.");

        // Creating a connection.
        try {
            socket = new Socket("localhost", 2022);
            outputStream = new DataOutputStream(socket.getOutputStream());
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (Exception e) {
            System.out.println("! Client was not able to establish a connection with the server. ");
            return;
        }

        System.out.println("! The connection has been successfully created.");

        try {
            // Lettura da tastiera
            System.out.print("> ");
            Scanner scan = new Scanner(System.in);
            message = scan.nextLine();
            scan.close();
            // Invio del messaggio
            sendMessage(outputStream, message+"\n");
            System.out.println("! Message sent.");
        } catch (Exception e) { e.printStackTrace(); }

        try {
            String message = reader.readLine();
            System.out.println("! Received '" + message  + "'.");
        } catch (Exception e) { }

        try { 
            socket.close();
        } catch (Exception e) { }
    }
}
