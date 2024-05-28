package co.edu.uptc.pojos;

import java.io.Serializable;

public class ClientPojo implements Serializable {
    
    private String ipAddress;
    private int clientsAmount;
    private boolean isPlayer;

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public boolean isPlayer() {
        return isPlayer;
    }

    public void setPlayer(boolean isPlayer) {
        this.isPlayer = isPlayer;
    }

    public int getClientsAmount() {
        return clientsAmount;
    }

    public void setClientsAmount(int clientsAmount) {
        this.clientsAmount = clientsAmount;
    } 
    
}
