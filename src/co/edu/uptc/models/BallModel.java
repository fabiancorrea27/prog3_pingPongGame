package co.edu.uptc.models;

import co.edu.uptc.pojos.BallPojo;
import co.edu.uptc.utils.Util;

public class BallModel {

    private BallPojo ball;
    private int movementSpeed;
    private MovementDirection movementDirection;
    private int horizontalLimit;

    public BallModel() {
        ball = new BallPojo();
    }

    public void startMovement() {
        Thread movementThread = new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    Util.sleep(1000);
                    move();
                }
            }

        });
    }

    private void move() {
        if (movementDirection == MovementDirection.RIGHT) {
            moveRight();
        } else if (movementDirection == MovementDirection.LEFT) {
            moveLeft();
        }
    }

    private void moveRight() {
        if ((ball.getxCoordinate() + ball.getSize()) <= horizontalLimit) {
            ball.setxCoordinate(ball.getxCoordinate() + movementSpeed);
        } else {
            this.movementDirection = MovementDirection.LEFT;
        }
    }

    private void moveLeft() {
        ball.setxCoordinate(ball.getxCoordinate() + movementSpeed);
    }

}
