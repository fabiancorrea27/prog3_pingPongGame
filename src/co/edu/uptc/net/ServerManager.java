package co.edu.uptc.net;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import co.edu.uptc.pojos.ClientPojo;

public class ServerManager {

    private ServerSocket serverSocket;
    private List<ClientPojo> clients;
    private boolean isSearching;

    public ServerManager() {
        clients = new ArrayList<>();
    }
    public void begin() {
        try {
            serverSocket = new ServerSocket(9001);
            waitForClients(serverSocket);
        } catch (IOException e) {
            System.out.println("Server fail");
            return;
        }

    }

    private void waitForClients(ServerSocket serverSocket) {
        isSearching = true;
        Thread waitForClientsThread = new Thread(new Runnable() {

            @Override
            public void run() {
                while (isSearching) {
                    Socket socketClient;
                    try {
                        socketClient = serverSocket.accept();
                        ClientPojo client = new ClientPojo();
                        client.setIpAddress(socketClient.getInetAddress().getHostAddress());
                        clients.add(client);
                        sendClientsAmount();
                    } catch (IOException e) {
                        System.out.println("Server fail");
                        return;
                    }
        
                }
            }
            
        });
        waitForClientsThread.setName("Wait For Clients Thread");
        waitForClientsThread.start();
    }

    private void sendClientsAmount(){
        for(ClientPojo clientPojo : clients){
            try {
                clientPojo.setClientsAmount(clients.size());
                Socket socket = new Socket(clientPojo.getIpAddress(), 9002);
               ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
               output.writeObject(clientPojo);
               socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void beginEvents(){
        isSearching = false;
        clients.get(0).setPlayer(true);
        clients.get(1).setPlayer(true);

    }
    public boolean isSearching() {
        return isSearching;
    }
    public List<ClientPojo> getClients() {
        return clients;
    }

}
