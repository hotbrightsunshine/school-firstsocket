package it.parisio;

import java.io.DataOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class SlayThread implements Runnable{

    Socket              socket;
    BufferedReader      reader;
    DataOutputStream    outputStream;

    public SlayThread(Socket _socket) throws Exception {
        socket = _socket;
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        outputStream = new DataOutputStream(socket.getOutputStream());
    }

    @Override
    public void run() {
        System.out.println("New connection accepted (" + this.toString() + ").");
        // reply
        try {
            String msg = reader.readLine();
            System.out.println("Read '" + msg + "'.");
            msg = msg.toUpperCase();
            outputStream.writeBytes(msg + "\n");
        } catch (Exception e){};
        System.out.println("New connection ended (" + this.toString() + ").");
    }
    
}
