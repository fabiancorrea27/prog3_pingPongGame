package co.edu.uptc.models.Server;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import co.edu.uptc.net.ServerManager;
import co.edu.uptc.pojos.BallPojo;
import co.edu.uptc.pojos.RacketPojo;
import co.edu.uptc.presenters.ContractServerPlay;
import co.edu.uptc.presenters.ContractServerPlay.Presenter;
import co.edu.uptc.utils.DirectionEnum;
import co.edu.uptc.utils.Util;

public class ServerGameManager implements ContractServerPlay.Model {

    private ContractServerPlay.Presenter presenter;
    private ServerBallModel ballModel;
    private List<ServerRacketModel> racketsModel;
    private int horizontalLimit;
    private int verticalLimit;
    private double horizontalDrawScale;
    private double verticalDrawScale;
    private ServerManager server;

    public ServerGameManager() {
        server = new ServerManager();
    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void start() {
        server.begin();
        lookForGameStart();
    }

    private boolean lookForGameStart() {
        Thread lookForGameStartThread = new Thread(new Runnable() {
            @Override
            public void run() {
                int clientsAmount = 0;
                // Look if the server is searching clients, if it is not, the game has started
                while (server.isSearching()) {
                    // Change the amount of connected clients
                    if (server.getClients().size() != clientsAmount) {
                        clientsAmount = server.getClients().size();
                        presenter.changeClientsAmount(clientsAmount);
                    }
                    Util.sleep(100);
                }
            }
        });
        lookForGameStartThread.start();
        return true;
    }

    @Override
    public void startGame() {
        setLimits();
        calculateDrawScale();
        initModels();
        ballModel.startMovement();
    }

    private void initModels() {
        initBall();
        initRackets();

    }

    private void initBall() {
        ballModel = new ServerBallModel();
        ballModel.setHorizontalLimit(horizontalLimit);
        ballModel.setVerticalLimit(verticalLimit);
        ballModel.setHorizontalDrawScale(horizontalDrawScale);
        ballModel.setVerticalDrawScale(verticalDrawScale);
        ballModel.configurePosition();
    }

    private void initRackets() {
        racketsModel = new ArrayList<ServerRacketModel>();
        racketsModel.add(createRacket(DirectionEnum.LEFT));
        racketsModel.add(createRacket(DirectionEnum.RIGHT));
    }

    private ServerRacketModel createRacket(DirectionEnum direction) {
        ServerRacketModel racketModel = new ServerRacketModel();
        racketModel.setHorizontalLimit(horizontalLimit);
        racketModel.setVerticalLimit(verticalLimit);
        racketModel.setHorizontalDrawScale(horizontalDrawScale);
        racketModel.setVerticalDrawScale(verticalDrawScale);
        racketModel.setPosition(direction);
        racketModel.configurePosition();
        racketModel.calculateRacketDrawScale();
        return racketModel;
    }

    private void setLimits() {
        this.horizontalLimit = server.getClients().size() * 1000;
        this.verticalLimit = 600;
    }

    private void calculateDrawScale() {
        horizontalDrawScale = 1000.0 / horizontalLimit;
        verticalDrawScale = 600.0 / verticalLimit;
    }

    @Override
    public BallPojo getBallPojoToDraw() {
        return ballModel.getBallPojoToDraw();
    }

    @Override
    public List<RacketPojo> getRacketsPojoToDraw() {
        List<RacketPojo> racketPojosToDraw = new ArrayList<RacketPojo>();
        for (ServerRacketModel racketModel : racketsModel) {
            racketPojosToDraw.add(racketModel.getRacketPojoToDraw());
        }
        return racketPojosToDraw;
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

    @Override
    public boolean checkMinClientsAmount() {
        return server.getClients().size() >= 2;
    }

}
