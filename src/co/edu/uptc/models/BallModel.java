package co.edu.uptc.models;

import co.edu.uptc.pojos.BallPojo;
import co.edu.uptc.utils.DirectionEnum;
import co.edu.uptc.utils.Util;

public class BallModel {

    private BallPojo ballPojo;
    private int movementSpeed;
    private DirectionEnum movementDirection;
    private int horizontalLimit;
    private int verticalLimit;

    public BallModel() {
        ballPojo = new BallPojo();
        movementSpeed = 10;
        ballPojo.setSize(20);
        chooseRandomDirection();
    }

    private void chooseRandomDirection(){
        int randomNumber = (int) (Math.random() * 2);
        if(randomNumber == 0){
            this.movementDirection = DirectionEnum.RIGHT;
        } else {
            this.movementDirection = DirectionEnum.LEFT;
        }
    }

    public void startMovement() {
        Thread movementThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Util.sleep(60);
                    move();
                }
            }
        });
        movementThread.setName("BallMovement");
        movementThread.start();
    }

    private void move() {
        if (movementDirection == DirectionEnum.RIGHT) {
            moveRight();
        } else if (movementDirection == DirectionEnum.LEFT) {
            moveLeft();
        }
    }

    private void moveRight() {
        if ((ballPojo.getxCoordinate() + ballPojo.getSize()) < horizontalLimit) {
            ballPojo.setxCoordinate(ballPojo.getxCoordinate() + movementSpeed);
        } else {
            ballPojo.setxCoordinate(horizontalLimit - ballPojo.getSize());
            this.movementDirection = DirectionEnum.LEFT;
        }
    }

    private void moveLeft() {
        if (ballPojo.getxCoordinate() > 0) {
            ballPojo.setxCoordinate(ballPojo.getxCoordinate() - movementSpeed);
        } else {
            ballPojo.setxCoordinate(0);
            this.movementDirection = DirectionEnum.RIGHT;
        }
    }

    public void configurePosition(){
        ballPojo.setxCoordinate(horizontalLimit / 2 - ballPojo.getSize() / 2);
       ballPojo.setyCoordinate(verticalLimit / 2 - ballPojo.getSize() / 2);
    }

    public BallPojo getBallPojo() {
        return ballPojo;
    }

    public void setHorizontalLimit(int horizontalLimit) {
        this.horizontalLimit = horizontalLimit;
    }

    public void setVerticalLimit(int verticalLimit) {
        this.verticalLimit = verticalLimit;
    }
    
}
