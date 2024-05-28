package co.edu.uptc.models;

import co.edu.uptc.pojos.RacketPojo;
import co.edu.uptc.utils.DirectionEnum;

public class RacketModel {
    private RacketPojo racket;
    private int movementSpeed;
    private int verticalLimit;
    private int horizontalLimit;
    private DirectionEnum position;

    public RacketModel() {
        racket = new RacketPojo();
        racket.setHeight(60);
        racket.setWidth(20);
        movementSpeed = 10;
    }

    public void move(DirectionEnum movementDirection) {
        if (movementDirection == DirectionEnum.UP) {
            moveUp();
        } else if (movementDirection == DirectionEnum.DOWN) {
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
        if (position == DirectionEnum.LEFT) {
            racket.setxCoordinate(10);
        } else if (position == DirectionEnum.RIGHT) {
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

    public void setPosition(DirectionEnum position) {
        this.position = position;
    }

}
