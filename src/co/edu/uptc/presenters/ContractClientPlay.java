package co.edu.uptc.presenters;

import java.util.List;

import co.edu.uptc.pojos.BallPojo;
import co.edu.uptc.pojos.RacketPojo;

public interface ContractClientPlay {

    public interface Model {
        public void setPresenter(Presenter presenter);
        public void start();   
        public BallPojo getBallPojoToDraw();
        public RacketPojo getRacketPojoToDraw();
        public void setHorizontalLimit(int horizontalLimit);
        public void setVerticalLimit(int verticalLimit);
        public void racketMovement(int keyCode);
        public boolean checkServerIp(String ipAdress);
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
        public RacketPojo getRacketsPojoToDraw();
        public void racketsMovement(int keyCode);
        public boolean checkServerIp(String ipAdress);
    }
}
