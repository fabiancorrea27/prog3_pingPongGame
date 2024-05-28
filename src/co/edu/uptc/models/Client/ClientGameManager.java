package co.edu.uptc.models.Client;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import co.edu.uptc.net.ClientManager;
import co.edu.uptc.pojos.BallPojo;
import co.edu.uptc.pojos.RacketPojo;
import co.edu.uptc.presenters.ContractClientPlay;
import co.edu.uptc.presenters.ContractClientPlay.Presenter;
import co.edu.uptc.utils.DirectionEnum;
import co.edu.uptc.utils.Util;

public class ClientGameManager implements ContractClientPlay.Model {

    private ContractClientPlay.Presenter presenter;
    private ClientBallModel ballModel;
    private List<ClientRacketModel> racketsModel;
    private int horizontalLimit;
    private int verticalLimit;
    private ClientManager client;
    private boolean isWaiting;

    public ClientGameManager() {
        client = new ClientManager();
    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void start() {
        presenter.beginGame();
        initModels();

    }

    private void initModels() {
        initBall();
        initRackets();
    }

    private void initBall() {
        ballModel = new ClientBallModel();
    }

    private void initRackets() {
        racketsModel = new ArrayList<ClientRacketModel>();
        racketsModel.add(initRacket());
    }

    private ClientRacketModel initRacket() {
        ClientRacketModel racketModel = new ClientRacketModel();
        racketModel.setHorizontalLimit(horizontalLimit);
        racketModel.setVerticalLimit(verticalLimit);
        racketModel.setScale(1);
        racketModel.configureRacketPojoToShow();
        return racketModel;
    }

    @Override
    public BallPojo getBallPojo() {
        return ballModel.getBallPojo();
    }

    @Override
    public List<RacketPojo> getRacketsPojo() {
        List<RacketPojo> racketPojos = new ArrayList<RacketPojo>();
        for (ClientRacketModel racketModel : racketsModel) {
            racketPojos.add(racketModel.getRacketPojo());
        }
        return racketPojos;
    }

    @Override
    public void setHorizontalLimit(int horizontalLimit) {
        this.horizontalLimit = horizontalLimit;

    }

    @Override
    public void setVerticalLimit(int verticalLimit) {
        this.verticalLimit = verticalLimit;
        if (racketsModel != null) {
            for (ClientRacketModel racketModel : racketsModel) {
                racketModel.setVerticalLimit(verticalLimit);
            }
        }
    }

    @Override
    public void racketsMovement(int keyCode) {
        System.out.println(KeyEvent.getKeyText(keyCode));
        if ((racketsModel.get(0).getPosition() != null) && (racketsModel.get(0).getPosition() == DirectionEnum.LEFT)) {
            moveLeftRacket(keyCode);
        } else {
            moveRightRacket(keyCode);
        }
    }

    private void moveLeftRacket(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_W:
                racketsModel.get(0).move(DirectionEnum.UP);
                break;
            case KeyEvent.VK_S:
                racketsModel.get(0).move(DirectionEnum.DOWN);
                break;
        }
    }

    private void moveRightRacket(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_UP:
                racketsModel.get(1).move(DirectionEnum.UP);
                break;
            case KeyEvent.VK_DOWN:
                racketsModel.get(1).move(DirectionEnum.DOWN);
                break;
        }
    }

    @Override
    public boolean checkServerIp(String ipAdress) {
        client.setServerIpAdress(ipAdress);
        try {
            client.pingWithServer();
        } catch (Exception e) {
            return false;
        }
        isWaiting = true;
        changePlayersAmount();
        return true;
    }

    private void changePlayersAmount(){
        Thread changePlayersAmountThread = new Thread(new Runnable() {

            @Override
            public void run() {
                while (isWaiting) {
                    presenter.changeClientsAmount(client.getClientsAmount());
                    Util.sleep(1000);
                }
            }
        });
        changePlayersAmountThread.setName("Change Players Amount Thread");
        changePlayersAmountThread.start();
    }
}
