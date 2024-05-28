package co.edu.uptc.models.Client;

import co.edu.uptc.pojos.RacketPojo;
import co.edu.uptc.utils.DirectionEnum;

public class ClientRacketModel {
    private RacketPojo racketPojo;
    private RacketPojo racketPojoToShow;
    private int verticalLimit;
    private int horizontalLimit;
    private int scale;
    private DirectionEnum position;
    private int movementSpeed;

    public ClientRacketModel() {
        racketPojo = new RacketPojo();
        racketPojo.setHeight(60);
        racketPojo.setWidth(20);
        racketPojoToShow = racketPojo;
    }

    public void configureRacketPojoToShow() {
        racketPojoToShow.setxCoordinate(racketPojo.getxCoordinate() * scale);
        racketPojoToShow.setyCoordinate(racketPojo.getyCoordinate() * scale);
        racketPojoToShow.setHeight(racketPojo.getHeight() * scale);
        racketPojoToShow.setWidth(racketPojo.getWidth() * scale);
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

    public void configurePosition() {
        configureHorizontalPosition();
    }

    private void configureHorizontalPosition() {
        if (position == DirectionEnum.LEFT) {
            racketPojo.setxCoordinate(10);
        } else if (position == DirectionEnum.RIGHT) {
            racketPojo.setxCoordinate((horizontalLimit - racketPojo.getWidth()) - 10);
        }
    }

    public void setHorizontalLimit(int horizontalLimit) {
        this.horizontalLimit = horizontalLimit;
    }

    public RacketPojo getRacketPojo() {
        return racketPojo;
    }

    public RacketPojo getRacketPojoToShow() {
        return racketPojoToShow;
    }

    public void setVerticalLimit(int verticalLimit) {
        this.verticalLimit = verticalLimit;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public void setPosition(DirectionEnum position) {
        this.position = position;
    }

    public DirectionEnum getPosition() {
        return position;
    }

}
