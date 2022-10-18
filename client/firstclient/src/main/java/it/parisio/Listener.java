package it.parisio;

import java.io.BufferedReader;
import java.io.IOException;

public class Listener implements Runnable {

    static public  BufferedReader      reader;

    boolean listen = true;

    @Override
    public void run() throws Exception {
        while(true){
            String message = "";
            try {
                message = reader.readLine();
            } catch (IOException e) {}
            if(message.equals("STOP")){
                
            }
        }
    }

}
