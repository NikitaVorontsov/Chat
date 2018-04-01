
package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(30333);
        int i=0;
        while (true){
        Socket soc = server.accept();
        System.out.println("Accept connection from "+ soc.getInetAddress().getCanonicalHostName());
        ClientThread th = new ClientThread(soc, "Client "+i);
        th.start();
        i++;
    }
    }
}

class ClientThread extends Thread {
    private final Socket soc;

    public ClientThread(Socket soc, String name) {
        super(name);
        this.soc = soc;
    }

    @Override
    public void run() {
        try {
              while (true){
                BufferedReader reader = new BufferedReader(new InputStreamReader(soc.getInputStream()));
                String str = reader.readLine();
                System.out.println("Got string "+ str); 
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(soc.getOutputStream()));
                if ("exit".equals(str)) {
                    writer.write("exit\n");
                    writer.flush();// сбрасываем буфер
                    break;
                }
                writer.write("accept\n");
                writer.flush(); 
            }
        } catch (IOException ex){   
            System.out.println("IOException in client thread "+ getName());
    }
    }
    
    
}