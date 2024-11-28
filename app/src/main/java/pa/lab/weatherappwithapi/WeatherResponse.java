package pa.lab.weatherappwithapi;

import java.util.List;

public class WeatherResponse {

    private Location location;
    private Current current;

    // Getter and Setter methods
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }

    // Location class to hold location details
    public static class Location {
        private String name;
        private String country;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }
    }

    // Current class to hold the current weather data
    public static class Current {
        private double temperature;
        private List<String> weather_descriptions; // Change this to List<String>

        public double getTemperature() {
            return temperature;
        }

        public void setTemperature(double temperature) {
            this.temperature = temperature;
        }

        public List<String> getWeather_descriptions() {  // Change return type to List<String>
            return weather_descriptions;
        }

        public void setWeather_descriptions(List<String> weather_descriptions) {
            this.weather_descriptions = weather_descriptions;
        }

    }
}
