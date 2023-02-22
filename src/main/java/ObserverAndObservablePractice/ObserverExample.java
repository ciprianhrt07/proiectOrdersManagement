package ObserverAndObservablePractice;

import java.util.Observable;
import java.util.Observer;

public class ObserverExample implements Observer {

    private  ObservableDemo weatherUpdate;

    @Override
    public void update(Observable o, Object arg) {

        weatherUpdate = (ObservableDemo) o;
        System.out.println("Weather Report is live .It s"+ weatherUpdate.getWeather());
    }

    public static void main(String[] args)
    {
        ObservableDemo observableDemo = new ObservableDemo(null);

        ObserverExample observer = new ObserverExample();

        observableDemo.addObserver(observer);

        observableDemo.setWeather("Bright and sunny...Let's play cricket!! ");
        observableDemo.setWeather("Raining Heavily!..Let's have hot Pakodas!!");

    }
}
