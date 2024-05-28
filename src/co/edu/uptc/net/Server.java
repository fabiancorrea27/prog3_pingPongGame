package co.edu.uptc.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import co.edu.uptc.pojos.ClientPojo;

public class Server {

    private ServerSocket serverSocket;
    private List<ClientPojo> clients;
    private boolean isSearching;

    public Server() {
        clients = new ArrayList<>();
    }
    public void begin() {
        try {
            serverSocket = new ServerSocket(9001);
            waitForClients(serverSocket);
            beginEvents();
        } catch (IOException e) {
            System.out.println("Server fail");
            return;
        }

    }

    private void waitForClients(ServerSocket serverSocket) {
        isSearching = true;
        while (isSearching) {
            Socket socketClient;
            try {
                socketClient = serverSocket.accept();
                ClientPojo client = new ClientPojo();
                client.setIpAddress(socketClient.getInetAddress().getHostAddress());
                clients.add(client);
            } catch (IOException e) {
                System.out.println("Server fail");
                return;
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
