package co.edu.uptc.presenters;

import java.util.List;

import co.edu.uptc.pojos.BallPojo;
import co.edu.uptc.pojos.RacketPojo;

public interface ContractServerPlay {

    public interface Model {
        public void setPresenter(Presenter presenter);
        public void start();   
        public void startGame();
        public BallPojo getBallPojoToDraw();
        public List<RacketPojo> getRacketsPojoToDraw();
        public void racketsMovement(int keyCode);
        public boolean checkMinClientsAmount();
    }

    public interface View {
        public void setPresenter(Presenter presenter);
        public void begin();
        public void beginGame();
        public void changeClientsAmount(int clientsAmount);
       
    }

    public interface Presenter {
        public void setModel(Model model);
        public void setView(View view);
        public void makeMVP();
        public void begin();

        // view
        public void beginGame();
        public void changeClientsAmount(int clientsAmount);
        
        
        // model
        public BallPojo getBallPojoToDraw();
        public List<RacketPojo> getRacketsPojoToDraw();
        public boolean checkMinClientsAmount();
    }
}
