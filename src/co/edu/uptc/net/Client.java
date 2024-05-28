package co.edu.uptc.net;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {

    private String JSONMessage;
    private String serverIpAdress;
    

    
    public void begin(){
        try {
            Socket socket = new Socket(serverIpAdress, 9001);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF(JSONMessage);
            socket.close();

        } catch (IOException e) {
            System.out.println("Client fail");
        }
    }
}
