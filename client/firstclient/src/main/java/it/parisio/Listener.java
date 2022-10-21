package it.parisio;

import java.io.BufferedReader;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Listener implements Runnable {

    DataOutputStream output;
    Scanner scan;
    Socket socket;
    //boolean listen = true;

    Listener(DataOutputStream d, Socket s){
        output = d;
        socket = s;
        scan = new Scanner(System.in);
    }

    private void send(String msg){
        try {
            output.writeBytes(msg+"\n");
        } catch (IOException e) {
            synchronized (System.out) {
                System.out.println("Couldn't send a message.");
            }
            
        }
    }

    @Override
    public void run() {
        while(!socket.isClosed()){
            String msg = null;
            try {
                msg = scan.nextLine();
            } catch (Exception e) { 
                continue;
            }

            if (msg.equals("/disconnect")){
                send("DISCONNECT");
            } else if (msg.equals("/stop")){
                send("STOPALL");
            } else {
                send(msg);
            }
            
        }
    }

}
