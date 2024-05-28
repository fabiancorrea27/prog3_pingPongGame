package co.edu.uptc.views.Server;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import co.edu.uptc.presenters.ContractServerPlay;

public class ServerGameFrame extends JFrame {

    private ServerDashboard dashboard;
    private ServerGamePanel gamePanel;

    public ServerGameFrame() {
        this.setSize(1000, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Ping Pong Game");
        gamePanel = new ServerGamePanel(this);
        this.add(gamePanel);
    }

    public void begin() {
        this.setVisible(true);
        gamePanel.begin();
    }

    public ContractServerPlay.Presenter getPresenter() {
        return dashboard.getPresenter();
    }

    public void setDashboard(ServerDashboard dashboard) {
        this.dashboard = dashboard;
    }

}
