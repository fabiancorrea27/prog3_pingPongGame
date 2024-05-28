package co.edu.uptc.models.Client;

import co.edu.uptc.pojos.RacketPojo;
import co.edu.uptc.utils.DirectionEnum;

public class ClientRacketModel {
    private RacketPojo racketPojo;
    private RacketPojo racketPojoToDraw;
    private int verticalLimit;
    private int movementSpeed;
    private int boardPosition;

    public ClientRacketModel() {
        racketPojo = new RacketPojo();
        racketPojo.setHeight(60);
        racketPojo.setWidth(20);
        racketPojoToDraw = new RacketPojo();
        racketPojoToDraw.setHeight(racketPojo.getHeight());
        racketPojoToDraw.setWidth(racketPojo.getWidth());
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

    public void calculateRacketPosition(){
        racketPojoToDraw.setxCoordinate(racketPojo.getxCoordinate() - ((1000 - 16) * boardPosition));
        racketPojoToDraw.setyCoordinate(racketPojo.getyCoordinate());
    }


    public RacketPojo getRacketPojo() {
        return racketPojo;
    }

    public void setVerticalLimit(int verticalLimit) {
        this.verticalLimit = verticalLimit;
    }

    public RacketPojo getRacketPojoToDraw() {
        return racketPojoToDraw;
    }

}
