package co.edu.uptc.models;

import co.edu.uptc.pojos.RacketPojo;

public class RacketModel {
    private RacketPojo racket;
    private int movementSpeed;
    private int verticalLimit;
    private int horizontalLimit;
    private Direction position;

    public RacketModel() {
        racket = new RacketPojo();
        racket.setHeight(60);
        racket.setWidth(20);
    }

    public void move(Direction movementDirection) {
        if (movementDirection == Direction.UP) {
            moveUp();
        } else if (movementDirection == Direction.DOWN) {
            moveDown();
        }
    }

    private void moveUp() {
        if (racket.getyCoordinate() >= 0) {
            racket.setyCoordinate(racket.getyCoordinate() - movementSpeed);
        } else {
            racket.setyCoordinate(0);
        }
    }

    private void moveDown() {
        if ((racket.getyCoordinate() + racket.getHeight()) <= (verticalLimit)) {
            racket.setyCoordinate(racket.getyCoordinate() + movementSpeed);
        } else {
            racket.setyCoordinate(verticalLimit - racket.getHeight());
        }
    }

    public void configurePosition() {
        configureHorizontalPosition();
        configureVerticalPosition();
    }

    private void configureHorizontalPosition() {
        if (position == Direction.LEFT) {
            racket.setxCoordinate(10);
        } else if (position == Direction.RIGHT) {
            racket.setxCoordinate((horizontalLimit - racket.getWidth()) - 10);
        }
    }

    private void configureVerticalPosition() {
        racket.setyCoordinate((verticalLimit / 2) - (racket.getHeight() / 2));
    }

    public void setHorizontalLimit(int horizontalLimit) {
        this.horizontalLimit = horizontalLimit;
    }

    public RacketPojo getRacket() {
        return racket;
    }

    public void setMovementSpeed(int movementSpeed) {
        this.movementSpeed = movementSpeed;
    }

    public void setVerticalLimit(int verticalLimit) {
        this.verticalLimit = verticalLimit;
    }

    public void setPosition(Direction position) {
        this.position = position;
    }

}
