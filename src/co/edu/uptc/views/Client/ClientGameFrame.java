package co.edu.uptc.views.Client;

import javax.swing.JFrame;

import co.edu.uptc.presenters.ContractClientPlay;

public class ClientGameFrame extends JFrame {

    private ContractClientPlay.Presenter presenter;
    private ClientGamePanel gamePanel;

    public ClientGameFrame() {
        this.setSize(1000, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Ping Pong Game");
        gamePanel = new ClientGamePanel();
        gamePanel.setGameFrame(this);
        this.add(gamePanel);
    }

    public void begin() {
        this.setVisible(true);
        gamePanel.begin();
    }

    public ContractClientPlay.Presenter getPresenter() {
        return presenter;
    }

    public void setPresenter(ContractClientPlay.Presenter presenter) {
        this.presenter = presenter;
    }

}
