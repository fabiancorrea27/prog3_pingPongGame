package co.edu.uptc.models.Client;

import co.edu.uptc.pojos.BallPojo;

public class ClientBallModel {

    private BallPojo ballPojo;
    private BallPojo ballPojoToDraw;
    private int boardPosition;

    public ClientBallModel() {
        ballPojo = new BallPojo();
        ballPojo.setSize(20);
        ballPojoToDraw = new BallPojo();
        ballPojo.setSize(ballPojo.getSize());
    }

    public void calculateBallPojoToDrawPosition() {
        ballPojoToDraw.setxCoordinate(ballPojo.getxCoordinate() - ((1000 - 16) * boardPosition));
        ballPojoToDraw.setyCoordinate(ballPojo.getyCoordinate());
        ballPojoToDraw.setSize(ballPojo.getSize());
        System.out.println(ballPojoToDraw.getxCoordinate() + " " + ballPojoToDraw.getyCoordinate());
    }

    public BallPojo getBallPojo() {
        return ballPojo;
    }

    public BallPojo getBallPojoToDraw() {
        return ballPojoToDraw;
    }

    public void setBoardPosition(int boardPosition) {
        this.boardPosition = boardPosition;
    }

    public void setBallPojo(BallPojo ballPojo) {
        this.ballPojo = ballPojo;
    }

}
