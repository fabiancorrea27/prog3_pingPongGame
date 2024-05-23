package co.edu.uptc.pojos;

import java.awt.Rectangle;

public class Ball {
    private int xCoordinate;
    private int yCoordinate;
    private Rectangle size;

    
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
    public Rectangle getSize() {
        return size;
    }
    public void setSize(Rectangle size) {
        this.size = size;
    }
}
