package it.parisio;

public class Main {
    public static void main(String[] args) throws Exception {
        Server server = new Server();
        server.launch();
        for (Thread thread : server.threadlist) {
            thread.join();
        }
    }
}
