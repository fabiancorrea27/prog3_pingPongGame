package co.edu.uptc.models.Client;

import co.edu.uptc.pojos.BallPojo;
import co.edu.uptc.utils.PropertiesReader;

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
        int windowWidth = Integer.parseInt(PropertiesReader.getProperty("windowWidth"));
        ballPojoToDraw.setxCoordinate(ballPojo.getxCoordinate() - ((windowWidth - 16)* boardPosition));
        ballPojoToDraw.setyCoordinate(ballPojo.getyCoordinate());
        ballPojoToDraw.setSize(ballPojo.getSize());
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
