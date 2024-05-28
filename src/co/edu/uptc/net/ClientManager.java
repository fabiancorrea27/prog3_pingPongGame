package co.edu.uptc.net;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import co.edu.uptc.pojos.ClientPojo;

public class ClientManager {

    
    private String serverIpAdress;
    private int clientsAmount;

    public ClientManager(){
        listenServerPackages();
    }
    
    public void begin(){
        try {
            Socket socket = new Socket(serverIpAdress, 9001);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listenServerPackages(){
        Thread listenServerPackagesThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ServerSocket serverSocket = new ServerSocket(9002);
                    while(true){
                        Socket socket = serverSocket.accept();
                        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                        ClientPojo client = (ClientPojo) input.readObject();
                        clientsAmount = client.getClientsAmount();
                        socket.close();
                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        listenServerPackagesThread.setName("Listen Server Packages Thread");
        listenServerPackagesThread.start();
       
    }

    public void pingWithServer() throws UnknownHostException, IOException{
        Socket socket = new Socket(serverIpAdress, 9001);
        socket.close();
    }

    public String getServerIpAdress() {
        return serverIpAdress;
    }

    public void setServerIpAdress(String serverIpAdress) {
        this.serverIpAdress = serverIpAdress;
    }

    public int getClientsAmount() {
        return clientsAmount;
    }

    
}
