package co.edu.uptc.pojos;

import java.io.Serializable;

public class BallPojo implements Serializable {
    private int xCoordinate;
    private int yCoordinate;
    private int size;

    public int getxCoordinate() {
        return xCoordinate;
    }
    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }
    public int getyCoordinate() {
        return yCoordinate;
    }
    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }    

    
}
