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

    public SlayThread(Socket _socket, Server server) throws Exception {
        socket = _socket;
        father = server;
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
                if (msg.equals("DISCONNECT")){
                    System.out.println("Disconnect Request");
                    ad_stop();
                    break;
                } else if (msg.equals("STOPALL")){
                    System.out.println("Stop Request");
                    father.slay();
                    break;
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

    public void ad_stop() throws Exception {
        outputStream.writeBytes("STOP\n");
        socket.close();
    }
    
}
