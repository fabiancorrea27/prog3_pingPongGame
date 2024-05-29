package co.edu.uptc.views.Server;

import java.awt.Graphics;
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
        int boardWidth = gameFrame.getPresenter().getAdjustedHorizontalLimit();
        int boardHeight = gameFrame.getPresenter().getAdjustedVerticalLimit();
        g.drawLine(0, boardHeight / 2, boardWidth, boardHeight / 2);
        if (ball != null) {
            g.fillOval(ball.getxCoordinate(), ball.getyCoordinate(), ball.getSize(), ball.getSize());
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
