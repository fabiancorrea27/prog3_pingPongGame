package co.edu.uptc.pojos;

import java.io.Serializable;

public class ClientPojo implements Serializable {

    private String ipAddress;
    private int clientsAmount;
    private boolean isPlayer;
    private RacketPojo racketPojo;
    private BallPojo ballPojo;
    private int boardPosition;
    private boolean isStarted;

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

    public RacketPojo getRacketPojo() {
        return racketPojo;
    }

    public void setRacketPojo(RacketPojo racketPojo) {
        this.racketPojo = racketPojo;
    }

    public BallPojo getBallPojo() {
        return ballPojo;
    }

    public void setBallPojo(BallPojo ballPojo) {
        this.ballPojo = ballPojo;
    }

    public int getBoardPosition() {
        return boardPosition;
    }

    public void setBoardPosition(int boardOrder) {
        this.boardPosition = boardOrder;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public void setStarted(boolean start) {
        this.isStarted = start;
    }   

}
