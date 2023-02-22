package ObserverAndObservablePractice;

import java.util.Observable;
import java.util.Observer;

public class ObservableDemo extends Observable {

    private String weather;

    public ObservableDemo(String weather)
    {
        this.weather = weather;
    }

    public String getWeather()
    {
        return this.weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
        setChanged();
        notifyObservers();
    }
}
