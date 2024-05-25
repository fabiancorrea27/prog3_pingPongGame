package co.edu.uptc.views;

import javax.swing.JFrame;

import co.edu.uptc.presenters.ContractPlay;
import co.edu.uptc.presenters.ContractPlay.Presenter;

public class Dashboard extends JFrame implements ContractPlay.View {

    private ContractPlay.Presenter presenter;
    private GamePanel gamePanel;

    public Dashboard() {
        this.setSize(1000, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        gamePanel = new GamePanel(this);
        this.add(gamePanel);
    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void begin() {
        this.setVisible(true);
        gamePanel.begin();
    }

    public ContractPlay.Presenter getPresenter() {
        return presenter;
    }

   
}
