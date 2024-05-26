package co.edu.uptc.models;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import co.edu.uptc.pojos.BallPojo;
import co.edu.uptc.pojos.RacketPojo;
import co.edu.uptc.presenters.ContractPlay;
import co.edu.uptc.presenters.ContractPlay.Presenter;

public class GameManager implements ContractPlay.Model {

    private ContractPlay.Presenter presenter;
    private BallModel ballModel;
    private List<RacketModel> racketsModel;
    private int horizontalLimit;
    private int verticalLimit;

    public GameManager() {

    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void start() {
        initModels();
        ballModel.startMovement();
    }

    private void initModels() {
        initBall();
        initRackets();

    }

    private void initBall() {
        ballModel = new BallModel();
        ballModel.setHorizontalLimit(horizontalLimit);
        ballModel.setVerticalLimit(verticalLimit);
        ballModel.configurePosition();
    }

    private void initRackets() {
        racketsModel = new ArrayList<RacketModel>();
        racketsModel.add(createRacket(DirectionEnum.LEFT));
        racketsModel.add(createRacket(DirectionEnum.RIGHT));
    }

    private RacketModel createRacket(DirectionEnum direction) {
        RacketModel racketModel = new RacketModel();
        racketModel.setHorizontalLimit(horizontalLimit);
        racketModel.setVerticalLimit(verticalLimit);
        racketModel.setPosition(direction);
        racketModel.configurePosition();
        return racketModel;
    }

    @Override
    public BallPojo getBallPojo() {
        return ballModel.getBallPojo();
    }

    @Override
    public List<RacketPojo> getRacketsPojo() {
        List<RacketPojo> racketPojos = new ArrayList<RacketPojo>();
        for (RacketModel racketModel : racketsModel) {
            racketPojos.add(racketModel.getRacket());
        }
        return racketPojos;
    }

    @Override
    public void setHorizontalLimit(int horizontalLimit) {
        this.horizontalLimit = horizontalLimit;
        if ((ballModel != null) && (racketsModel != null)) {
            ballModel.setHorizontalLimit(horizontalLimit);
            for (RacketModel racketModel : racketsModel) {
                racketModel.setHorizontalLimit(horizontalLimit);
            }
        }
    }

    @Override
    public void setVerticalLimit(int verticalLimit) {
        this.verticalLimit = verticalLimit;
        if (racketsModel != null) {
            for (RacketModel racketModel : racketsModel) {
                racketModel.setVerticalLimit(verticalLimit);
            }
        }
    }

    @Override
    public void racketsMovement(int keyCode) {
        System.out.println(KeyEvent.getKeyText(keyCode));
        switch (keyCode) {
            case KeyEvent.VK_W:
                racketsModel.get(0).move(DirectionEnum.UP);
                break;
            case KeyEvent.VK_S:
                racketsModel.get(0).move(DirectionEnum.DOWN);
                break;
            case KeyEvent.VK_UP:
                racketsModel.get(1).move(DirectionEnum.UP);
                break;
            case KeyEvent.VK_DOWN:
                racketsModel.get(1).move(DirectionEnum.DOWN);
                break;
        }
    }
}
