
package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException {
        Socket soc =new Socket("localhost",30333);
        while(true){
            Scanner scan = new Scanner(System.in);
            String inStr = scan.nextLine()+"\n";
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(soc.getOutputStream()));
            writer.write(inStr);
            writer.flush();
            BufferedReader reader = new BufferedReader(new InputStreamReader(soc.getInputStream()));
            String str = reader.readLine();
            System.out.println("Got "+ str+" from Server");
            if ("exit\n".equals(inStr)) break;
        }
        
    }
    
}
