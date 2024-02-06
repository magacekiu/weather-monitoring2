package edu.iu.habahram.weathermonitoring.model;

import org.springframework.stereotype.Component;

@Component
public class StatisticsDisplay implements Observer, DisplayElement {
    private float maxTemp = 0.0f;
    private float minTemp = 200;
    private float tempSum= 0.0f;
    private int numReadings;
    private Subject weatherData;

    public StatisticsDisplay(Subject weatherData) {
        this.weatherData = weatherData;
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        tempSum += temperature;
        numReadings++;

        if (temperature > maxTemp) {
            maxTemp = temperature;
        }
 
        if (temperature < minTemp) {
            minTemp = temperature;
        }
    }

    @Override
    public String display() {
        return String.format("<div>Weather Stats:<br>Avg. temp: %f<br>Min. temp: %f<br>Max temp: %f</div>",
                (tempSum / numReadings), minTemp, maxTemp);
    }

    @Override
    public String name() {
        return "Weather Stats";
    }

    @Override
    public String id() {
        return "weather-stats";
    }

    public void subscribe() {
        weatherData.registerObserver(this);
    }

    public void unsubscribe() {
        weatherData.removeObserver(this);
    }
}
