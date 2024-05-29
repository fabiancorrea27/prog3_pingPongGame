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
import co.edu.uptc.utils.PropertiesReader;
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
        server.beginEvents();
        sendAndReceiveInformationFromClient();
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
        racketModel.getRacketPojo().setPosition(direction);
        racketModel.configurePosition();
        racketModel.calculateRacketDrawScale();
        return racketModel;
    }

    private void sendAndReceiveInformationFromClient() {
        Thread sendClientInformationThread = new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    server.setBallPojo(ballModel.getBallPojo());
                    server.sendClientsPackage();
                    RacketPojo racketPojoAdjusted = racketPojoAdjusted(racketsModel.get(0).getRacketPojo());
                    racketsModel.get(0).setRacketPojo(racketPojoAdjusted);
                    racketPojoAdjusted = racketPojoAdjusted(racketsModel.get(1).getRacketPojo());
                    racketsModel.get(1).setRacketPojo(racketPojoAdjusted);
                }
            }

        });
        sendClientInformationThread.setName("Send Client Information Thread");
        sendClientInformationThread.start();
    }

    private RacketPojo racketPojoAdjusted(RacketPojo racketPojo) {
        RacketPojo racketPojoAdjusted = new RacketPojo();
        racketPojoAdjusted.setxCoordinate(racketPojo.getxCoordinate()
                + ((Integer.parseInt(PropertiesReader.getProperty("windowWidth")) * server.getClients().size()) - 16));
        racketPojoAdjusted.setyCoordinate(racketPojo.getyCoordinate());
        racketPojoAdjusted.setAvailable(racketPojo.isAvailable());
        racketPojoAdjusted.setPosition(racketPojo.getPosition());
        racketPojoAdjusted.setHeight(racketPojo.getHeight());
        racketPojoAdjusted.setWidth(racketPojo.getWidth());
        return racketPojoAdjusted;
    }

    private void setLimits() {
        this.horizontalLimit = server.getClients().size()
                * (Integer.parseInt(PropertiesReader.getProperty("windowWidth")) - 16);
        this.verticalLimit = Integer.parseInt(PropertiesReader.getProperty("windowHeight")) - 40;
    }

    private void calculateDrawScale() {
        double windowHeight = Double.parseDouble(PropertiesReader.getProperty("windowHeight"));
        double windowWidth = Double.parseDouble(PropertiesReader.getProperty("windowWidth"));
        horizontalDrawScale = (windowWidth - 16.0) / horizontalLimit;
        verticalDrawScale = (windowHeight - 40.0) / verticalLimit;
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
