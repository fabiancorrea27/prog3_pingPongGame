package co.edu.uptc.models.Client;

import co.edu.uptc.pojos.RacketPojo;
import co.edu.uptc.utils.DirectionEnum;

public class ClientRacketModel {
    private RacketPojo racketPojo;
    private int verticalLimit;
    private int horizontalLimit;
    private int movementSpeed;
    private int boardPosition;

    public ClientRacketModel() {
        racketPojo = new RacketPojo();
        racketPojo.setHeight(60);
        racketPojo.setWidth(20);

    }

    public void configureInitialPosition() {
        racketPojo.setyCoordinate(verticalLimit / 2 - (racketPojo.getHeight() / 2));
        configureXCoordinate();
    }

    private void configureXCoordinate() {
        if (racketPojo.getPosition() == DirectionEnum.LEFT) {
            racketPojo.setxCoordinate(10);
        } else if (racketPojo.getPosition() == DirectionEnum.RIGHT) {
            racketPojo.setxCoordinate(horizontalLimit - (racketPojo.getWidth() - 10));
        }
    }

    public void move(DirectionEnum movementDirection) {
        if (movementDirection == DirectionEnum.UP) {
            moveUp();
        } else if (movementDirection == DirectionEnum.DOWN) {
            moveDown();
        }
    }

    private void moveUp() {
        if (racketPojo.getyCoordinate() >= 0) {
            racketPojo.setyCoordinate(racketPojo.getyCoordinate() - movementSpeed);
        } else {
            racketPojo.setyCoordinate(0);
        }
    }

    private void moveDown() {
        if ((racketPojo.getyCoordinate() + racketPojo.getHeight()) <= (verticalLimit)) {
            racketPojo.setyCoordinate(racketPojo.getyCoordinate() + movementSpeed);
        } else {
            racketPojo.setyCoordinate(verticalLimit - racketPojo.getHeight());
        }
    }

    public RacketPojo getRacketPojo() {
        return racketPojo;
    }

    public void setVerticalLimit(int verticalLimit) {
        this.verticalLimit = verticalLimit;
    }

    public void setHorizontalLimit(int horizontalLimit) {
        this.horizontalLimit = horizontalLimit;
    }

    

}
