package co.edu.uptc.models;

import co.edu.uptc.pojos.RacketPojo;

public class RacketModel {
    private RacketPojo racket;
    private int movementSpeed;
    private int verticalLimit;

    public RacketModel() {
        racket = new RacketPojo();
        racket.setHeight(30);
        racket.setWidth(10);
        racket.setxCoordinate(10);
        racket.setyCoordinate(calculateMiddleYCoordinate());
    }

    public int calculateMiddleYCoordinate(){
        return (verticalLimit / 2) - (racket.getHeight() / 2);
    }

    public void move(MovementDirection movementDirection) {
        if(movementDirection == MovementDirection.UP){
            moveUp();
        } else if(movementDirection == MovementDirection.DOWN){
            moveDown();
        }
    }

    private void moveUp() {
        if(racket.getyCoordinate() >= 0){
            racket.setyCoordinate(racket.getyCoordinate() - movementSpeed);
        } else {
            racket.setyCoordinate(0);
        }
    }

    private void moveDown(){
        if((racket.getyCoordinate() + racket.getHeight()) <= (verticalLimit)){
            racket.setyCoordinate(racket.getyCoordinate() + movementSpeed);
        } else {
            racket.setyCoordinate(verticalLimit - racket.getHeight());
        }
    }
}
