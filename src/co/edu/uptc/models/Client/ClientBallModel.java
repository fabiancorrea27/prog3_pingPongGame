package co.edu.uptc.models.Client;

import co.edu.uptc.pojos.BallPojo;

public class ClientBallModel {

    private BallPojo ballPojo;
    private BallPojo ballPojoToShow;

    public ClientBallModel() {
        ballPojo = new BallPojo();
        ballPojo.setSize(20);
        ballPojoToShow = ballPojo;
    }

    public BallPojo getBallPojo() {
        return ballPojo;
    }
    
    public BallPojo getBallPojoToShow() {
        return ballPojoToShow;
    }


    
    
}
