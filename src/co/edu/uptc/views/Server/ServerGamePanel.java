package co.edu.uptc.views.Server;

import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;

import javax.swing.JPanel;

import co.edu.uptc.pojos.BallPojo;
import co.edu.uptc.pojos.RacketPojo;
import co.edu.uptc.utils.Util;

public class ServerGamePanel extends JPanel {

    private boolean running;
    private BallPojo ball;
    private List<RacketPojo> rackets;
    private ServerGameFrame gameFrame;

    public ServerGamePanel(ServerGameFrame dashboard) {
        this.gameFrame = dashboard;
        
    }
   
    // }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (ball != null) {
            g.drawOval(ball.getxCoordinate(), ball.getyCoordinate(), ball.getSize(), ball.getSize());
        }

        if (rackets != null) {
            for (RacketPojo racket : rackets) {
                g.drawRect(racket.getxCoordinate(), racket.getyCoordinate(), racket.getWidth(), racket.getHeight());
            }
        }
    }

    public void threadPaint() {
        int threadDelay = 5;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (running) {
                    Util.sleep(threadDelay);
                    loadObjectsPojo();
                    repaint();
                }
            }
        });
        thread.setName("Paint Thread");
        thread.start();
    }

    public void loadObjectsPojo() {
        this.ball = gameFrame.getPresenter().getBallPojoToDraw();
        this.rackets = gameFrame.getPresenter().getRacketsPojoToDraw();
    }

    public void begin() {
        running = true;
        threadPaint();
    }
}
