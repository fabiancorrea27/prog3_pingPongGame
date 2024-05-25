package co.edu.uptc.presenters;

import java.util.List;

import co.edu.uptc.models.GameManager;
import co.edu.uptc.pojos.BallPojo;
import co.edu.uptc.pojos.RacketPojo;
import co.edu.uptc.presenters.ContractPlay.Model;
import co.edu.uptc.presenters.ContractPlay.View;
import co.edu.uptc.views.Dashboard;

public class Presenter implements ContractPlay.Presenter{

    private ContractPlay.Model model;
    private ContractPlay.View view;

    @Override
    public void setModel(Model model) {
        this.model = model;
    }

    @Override
    public void setView(View view) {
        this.view = view;
    }

    @Override
    public void begin() {
        makeMVP();
        view.begin();
        model.start();
    }

    @Override
    public void makeMVP() {
        Dashboard dashboard = new Dashboard();
        dashboard.setPresenter(this);
        setView(dashboard);

        GameManager gameManager = new GameManager();
        gameManager.setPresenter(this);
        setModel(gameManager);
    }

    @Override
    public BallPojo getBallPojo() {
        return model.getBallPojo();
    }

    @Override
    public List<RacketPojo> getRacketsPojo() {
       return model.getRacketsPojo();
    }

    @Override
    public void setHorizontalLimit(int horizontalLimit) {
        model.setHorizontalLimit(horizontalLimit);
    }

    @Override
    public void setVerticalLimit(int verticalLimit) {
        model.setVerticalLimit(verticalLimit);
    }

 

   

}
