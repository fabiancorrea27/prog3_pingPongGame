package co.edu.uptc.views.Server;

import javax.swing.JFrame;

import co.edu.uptc.presenters.ContractPlay;
import co.edu.uptc.presenters.ContractPlay.Presenter;

public class ServerDashboard extends JFrame implements ContractPlay.View {

    private ContractPlay.Presenter presenter;
    private GameFrame gameFrame = new GameFrame();
    private WaitingPanel waitingPanel = new WaitingPanel();

    public ServerDashboard() {
        this.setSize(600, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.add(waitingPanel);
    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void begin() {
        this.setVisible(true);
    }

    public ContractPlay.Presenter getPresenter() {
        return presenter;
    }

    @Override
    public void beginGame() {
        gameFrame.begin();
    }

    @Override
    public void changeClientsAmount(int clientsAmount) {
        waitingPanel.changePlayersAmount(clientsAmount);
    }

}
