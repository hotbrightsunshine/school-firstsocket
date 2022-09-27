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
        try {
            socket = new Socket("localhost", 2022);
            outputStream = new DataOutputStream(socket.getOutputStream());
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (Exception e) {
            return;
        }
        System.out.println("The connection has been successfully created.");

        try {
            // Lettura da tastiera
            System.out.print("Inserisci una frase > ");
            Scanner scan = new Scanner(System.in);
            message = scan.nextLine();

            // Invio del messaggio
            sendMessage(outputStream, message+"\n");
            System.out.println("Message sent.");
        } catch (Exception e) { e.printStackTrace(); }

        try {
            String message = reader.readLine();
            System.out.println("Ho ricevuto: '" + message  + "'");
        } catch (Exception e) { }

        try { 
            socket.close();
        } catch (Exception e) { }
    }
}
