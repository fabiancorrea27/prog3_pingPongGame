package co.edu.uptc.presenters;

import java.util.List;

import co.edu.uptc.pojos.BallPojo;
import co.edu.uptc.pojos.RacketPojo;

public interface ContractPlay {

    public interface Model {
        public void setPresenter(Presenter presenter);
        public void start();   
        public BallPojo getBallPojo();
        public List<RacketPojo> getRacketsPojo();
        public void setHorizontalLimit(int horizontalLimit);
        public void setVerticalLimit(int verticalLimit);
    }

    public interface View {
        public void setPresenter(Presenter presenter);
        public void begin();
       
    }

    public interface Presenter {
        public void setModel(Model model);
        public void setView(View view);
        public void makeMVP();
        public void begin();
        
        // model
        public BallPojo getBallPojo();
        public List<RacketPojo> getRacketsPojo();
        public void setHorizontalLimit(int horizontalLimit);
        public void setVerticalLimit(int verticalLimit);
        
    }
}
