package it.parisio;

import java.io.DataOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class SlayThread implements Runnable{

    Socket              socket;
    BufferedReader      reader;
    DataOutputStream    outputStream;
    Server              father;

    public SlayThread(Socket _socket, Server father) throws Exception {
        socket = _socket;
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        outputStream = new DataOutputStream(socket.getOutputStream());
    }

    @Override
    public void run() {
        System.out.println("New connection accepted (" + this.toString() + ").");
        // reply
        try {
            while(true) {
                String msg = reader.readLine();
                if (msg == "DISCONNECT"){
                    break;
                } else if (msg == "STOP"){
                    father.stop();
                } else {
                    System.out.println("Read '" + msg + "'.");
                    msg = msg.toUpperCase();
                    outputStream.writeBytes(msg + "\n");
                }
            }
            socket.close();
        } catch (Exception e){};
        System.out.println("New connection ended (" + this.toString() + ").");
    }
    
}
