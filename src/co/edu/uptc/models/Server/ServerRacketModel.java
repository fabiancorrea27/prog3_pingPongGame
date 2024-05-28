package co.edu.uptc.models.Server;

import co.edu.uptc.pojos.RacketPojo;
import co.edu.uptc.utils.DirectionEnum;

public class ServerRacketModel {
    private RacketPojo racketPojo;
    private RacketPojo racketPojoToDraw;
    private int movementSpeed;
    private int verticalLimit;
    private int horizontalLimit;
    private double horizontalDrawScale;
    private double verticalDrawScale;
    private DirectionEnum position;

    public ServerRacketModel() {
        racketPojo = new RacketPojo();
        racketPojo.setHeight(60);
        racketPojo.setWidth(20);
        racketPojoToDraw = new RacketPojo();
        movementSpeed = 10;
    }

    public void move(DirectionEnum movementDirection) {
        if (movementDirection == DirectionEnum.UP) {
            moveUp();
        } else if (movementDirection == DirectionEnum.DOWN) {
            moveDown();
        }
        calculateRacketDrawScale();
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

    public void calculateRacketDrawScale() {
        copyValuesToRacketPojoToDraw();
        racketPojoToDraw.setHeight((int) (racketPojo.getHeight() * verticalDrawScale));
        racketPojoToDraw.setWidth((int) (racketPojo.getWidth() * horizontalDrawScale));
        racketPojoToDraw.setxCoordinate((int) (racketPojo.getxCoordinate() * horizontalDrawScale));
        racketPojoToDraw.setyCoordinate((int) (racketPojo.getyCoordinate() * verticalDrawScale));
    }

    private void copyValuesToRacketPojoToDraw() {
        racketPojoToDraw.setHeight(racketPojo.getHeight());
        racketPojoToDraw.setWidth(racketPojo.getWidth());
        racketPojoToDraw.setxCoordinate(racketPojo.getxCoordinate());
        racketPojoToDraw.setyCoordinate(racketPojo.getyCoordinate());
    }

    public void configurePosition() {
        configureHorizontalPosition();
        configureVerticalPosition();
    }

    private void configureHorizontalPosition() {
        if (position == DirectionEnum.LEFT) {
            racketPojo.setxCoordinate(10);
        } else if (position == DirectionEnum.RIGHT) {
            racketPojo.setxCoordinate((horizontalLimit - racketPojo.getWidth()) - 25);
        }
    }

    private void configureVerticalPosition() {
        racketPojo.setyCoordinate((verticalLimit / 2) - (racketPojo.getHeight() / 2));
    }

    public void setHorizontalLimit(int horizontalLimit) {
        this.horizontalLimit = horizontalLimit;
    }

    public RacketPojo getRacketPojo() {
        return racketPojo;
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

    public RacketPojo getRacketPojoToDraw() {
        return racketPojoToDraw;
    }

    public void setHorizontalDrawScale(double horizontalDrawScale) {
        this.horizontalDrawScale = horizontalDrawScale;
    }

    public void setVerticalDrawScale(double verticalDrawScale) {
        this.verticalDrawScale = verticalDrawScale;
    }

}
