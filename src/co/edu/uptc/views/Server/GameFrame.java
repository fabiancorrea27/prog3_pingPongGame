package co.edu.uptc.views.Server;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import co.edu.uptc.presenters.ContractPlay;

public class GameFrame extends JFrame {

    private ServerDashboard dashboard;
    private GamePanel gamePanel;

    public GameFrame() {
        this.setSize(1000, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        putKeyListener();
        gamePanel = new GamePanel(this);
        this.add(gamePanel);
    }

  

    private void putKeyListener() {
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                dashboard.getPresenter().racketsMovement(e.getKeyCode());
            }
        });
    }

    public void begin(){
        gamePanel.begin();
    }

    public ContractPlay.Presenter getPresenter() {
        return dashboard.getPresenter();
    }

   
}
