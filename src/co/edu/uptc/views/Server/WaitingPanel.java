package co.edu.uptc.views.Server;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import co.edu.uptc.views.CustomComponents.WaitingLabel;

public class WaitingPanel extends JPanel {

    private JLabel lblIpText, lblIpAdress, lblPortText, lblPort, lblWait, lblPlayers, lblPlayersAmount;
    private int waitingMessageIndex = 0;

    public WaitingPanel() {
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.BLACK);
        initComponents();
        addComponents();
        waitingAnimation();
    }

    private void initComponents() {
        lblIpText = new WaitingLabel("IP:");
        lblIpAdress = new WaitingLabel("0");
        lblPortText = new WaitingLabel("Puerto:");
        lblPort = new WaitingLabel("9001");
        lblWait = new WaitingLabel("Esperando a que se conecten");
        lblWait.setPreferredSize(new Dimension(400, 30));
        lblPlayers = new WaitingLabel("Conectados:");
        lblPlayersAmount = new WaitingLabel("0");
    }

    private void addComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;
        this.add(lblIpText, gbc);
        this.add(lblIpAdress, gbc);
        this.add(lblPortText, gbc);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        this.add(lblPort, gbc);
        this.add(lblWait, gbc);
        gbc.gridwidth = 1;
        this.add(lblPlayers, gbc);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        this.add(lblPlayersAmount, gbc);
    }

    private void waitingAnimation() {
        String[] waitingMessages = { "Esperando a que se conecten", "Esperando a que se conecten.",
                "Esperando a que se conecten..", "Esperando a que se conecten..." };
        Timer timer = new Timer(500, e -> {
            lblWait.setText(waitingMessages[waitingMessageIndex]);
            waitingMessageIndex = (waitingMessageIndex + 1) % waitingMessages.length;
        });
        timer.start();
    }

    public void changePlayersAmount(int playersAmount) {
        lblPlayersAmount.setText(String.valueOf(playersAmount));
    }

    public void changeIpAdress(String ipAdress) {
        lblIpAdress.setText(ipAdress);
    }
}
