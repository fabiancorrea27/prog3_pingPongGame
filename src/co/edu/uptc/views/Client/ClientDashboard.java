package co.edu.uptc.views.Client;

import java.awt.event.ActionListener;

import javax.swing.JFrame;

import co.edu.uptc.presenters.ContractClientPlay;
import co.edu.uptc.presenters.ContractClientPlay.Presenter;
import co.edu.uptc.utils.RoleEnum;
import co.edu.uptc.views.WaitingPanel;

public class ClientDashboard extends JFrame implements ContractClientPlay.View {

    private ContractClientPlay.Presenter presenter;
    private InputAdressPanel inputAdressPanel = new InputAdressPanel(enterButtonActionListener());
    private WaitingPanel waitingPanel = new WaitingPanel(null, RoleEnum.CLIENT);

    public ClientDashboard() {
        this.setSize(600, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.add(inputAdressPanel);
    }
    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void begin() {
        this.setVisible(true);
    }

    private ActionListener enterButtonActionListener() {
        return e -> {
            if(presenter.checkServerIp(inputAdressPanel.getIpAdress())){
                changeToWaitingPanel();
            } else {
                inputAdressPanel.changeFailStatus("Servidor no encontrado");
            }
        };
    }
    private void changeToWaitingPanel() {
        this.remove(inputAdressPanel);
        this.add(waitingPanel);

    }

    @Override
    public void beginGame() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'beginGame'");
    }
    @Override
    public void changeClientsAmount(int clientsAmount) {
       waitingPanel.changePlayersAmount(clientsAmount);
    }

}
