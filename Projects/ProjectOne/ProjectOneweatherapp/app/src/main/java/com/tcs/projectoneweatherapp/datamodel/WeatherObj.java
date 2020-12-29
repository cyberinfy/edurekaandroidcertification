package com.tcs.projectoneweatherapp.datamodel;

import android.text.format.DateFormat;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import static java.lang.Math.round;

public class WeatherObj {
    private City city;
    private int cod;
    private double message;
    private int cnt;
    private List<WeatherItem> list;
    private String[] spinnerList;
    static String directions[] = {"N", "NE", "E", "SE", "S", "SW", "W", "NW"};

    public String getPopulation(){
        return String.valueOf(city.getPopulation());
    }

    public String[] getDatesList() {
        int i=0;
        spinnerList = new String[11];
        for(WeatherItem w : list){
            spinnerList[i++] = getDateFromUTCTimestamp(w.getSunrise());
        }
        return spinnerList;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public double getMessage() {
        return message;
    }

    public void setMessage(double message) {
        this.message = message;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public List<WeatherItem> getList() {
        return list;
    }

    public void setList(List<WeatherItem> list) {
        this.list = list;
    }

    public class City {
        private long id;
        private String name;
        private Coord coord;
        private String country;
        private long population;
        private long timezone;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Coord getCoord() {
            return coord;
        }

        public void setCoord(Coord coord) {
            this.coord = coord;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public long getPopulation() {
            Log.d("pop",population+"");
            return population;
        }

        public void setPopulation(long population) {
            this.population = population;
        }

        public long getTimezone() {
            return timezone;
        }

        public void setTimezone(long timezone) {
            this.timezone = timezone;
        }
    }

    private class Coord {
        private float lon;
        private float lat;

        public float getLon() {
            return lon;
        }

        public void setLon(float lon) {
            this.lon = lon;
        }

        public float getLat() {
            return lat;
        }

        public void setLat(float lat) {
            this.lat = lat;
        }
    }

    public class WeatherItem {
        private long dt;
        private long sunrise;
        private long sunset;
        private Temp temp;
        private FeelsLike feels_like;
        private float pressure;
        private float humidity;
        private List<Weather> weather;
        private float speed;
        private int deg;
        private float clouds;

        public List<ListItem> toListItemArray(){
            List<ListItem> listItems = new ArrayList<ListItem>();
            listItems.add(new ListItem("Description",weather.get(0).getDescription()));
            listItems.add(new ListItem("Pressure", String.valueOf(getPressure())+" hPa"));
            listItems.add(new ListItem("Humidity", String.valueOf(getHumidity())+" %"));
            listItems.add(new ListItem("Clouds",String.valueOf(clouds)+" %"));
            listItems.add(new ListItem("Wind Speed",String.valueOf(getSpeed())+" m/s"));
            listItems.add(new ListItem("Wind Degree, Direction",String.valueOf(getDeg())+" °, "+directions[(int)Math.round(((getDeg() % 360) / 45))%8]));
            listItems.add(new ListItem("Calculation Time",getDateFromUTCTimestamp(getDt())));
            listItems.add(new ListItem("Temperature",getTemperatureInCelcious(temp.getDay())));
            listItems.add(new ListItem("Feels Like",getTemperatureInCelcious(feels_like.getDay())));
            listItems.add(new ListItem("Temperature Minimum",getTemperatureInCelcious(temp.getMin())));
            listItems.add(new ListItem("Temperature Maximum",getTemperatureInCelcious(temp.getMax())));
            listItems.add(new ListItem("Temperature Morning",getTemperatureInCelcious(temp.getMorn())));
            listItems.add(new ListItem("Feels Like Morning",getTemperatureInCelcious(feels_like.getMorn())));
            listItems.add(new ListItem("Temperature Evening",getTemperatureInCelcious(temp.getEve())));
            listItems.add(new ListItem("Feels Like Evening",getTemperatureInCelcious(feels_like.getEve())));
            listItems.add(new ListItem("Temperature Night",getTemperatureInCelcious(temp.getNight())));
            listItems.add(new ListItem("Feels Like Night",getTemperatureInCelcious(feels_like.getNight())));
            listItems.add(new ListItem("Image url",weather.get(0).getIcon()));
            return listItems;
        }

        public long getDt() {
            return dt;
        }

        public void setDt(long dt) {
            this.dt = dt;
        }

        public long getSunrise() {
            return sunrise;
        }

        public void setSunrise(long sunrise) {
            this.sunrise = sunrise;
        }

        public long getSunset() {
            return sunset;
        }

        public void setSunset(long sunset) {
            this.sunset = sunset;
        }

        public Temp getTemp() {
            return temp;
        }

        public void setTemp(Temp temp) {
            this.temp = temp;
        }

        public FeelsLike getFeels_like() {
            return feels_like;
        }

        public void setFeels_like(FeelsLike feels_like) {
            this.feels_like = feels_like;
        }

        public float getPressure() {
            return pressure;
        }

        public void setPressure(float pressure) {
            this.pressure = pressure;
        }

        public float getHumidity() {
            return humidity;
        }

        public void setHumidity(float humidity) {
            this.humidity = humidity;
        }

        public List<Weather> getWeather() {
            return weather;
        }

        public void setWeather(List<Weather> weather) {
            this.weather = weather;
        }

        public float getSpeed() {
            return speed;
        }

        public void setSpeed(float speed) {
            this.speed = speed;
        }

        public int getDeg() {
            return deg;
        }

        public void setDeg(int deg) {
            this.deg = deg;
        }

        public float getClouds() {
            return clouds;
        }

        public void setClouds(float clouds) {
            this.clouds = clouds;
        }


        public String getDateFromUTCTimestamp(long mTimestamp) {
            String mDateFormate = "hh:mm a";
            String date = null;
            try {
                Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
                cal.setTimeInMillis(mTimestamp * 1000L);
                date = DateFormat.format(mDateFormate, cal.getTimeInMillis()).toString();

                SimpleDateFormat formatter = new SimpleDateFormat(mDateFormate);
                formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
                Date value = formatter.parse(date);

                SimpleDateFormat dateFormatter = new SimpleDateFormat(mDateFormate);
                dateFormatter.setTimeZone(TimeZone.getDefault());
                date = dateFormatter.format(value);
                return date;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return date;
        }

        public String getTemperatureInCelcious(float temp){
            return ((round((temp-273.15)*100))/100)+" °C";
        }
    }

    private class Temp {
        private float day;
        private float min;
        private float max;
        private float night;
        private float eve;
        private float morn;

        public float getDay() {
            return day;
        }

        public void setDay(float day) {
            this.day = day;
        }

        public float getMin() {
            return min;
        }

        public void setMin(float min) {
            this.min = min;
        }

        public float getMax() {
            return max;
        }

        public void setMax(float max) {
            this.max = max;
        }

        public float getNight() {
            return night;
        }

        public void setNight(float night) {
            this.night = night;
        }

        public float getEve() {
            return eve;
        }

        public void setEve(float eve) {
            this.eve = eve;
        }

        public float getMorn() {
            return morn;
        }

        public void setMorn(float morn) {
            this.morn = morn;
        }
    }

    private class FeelsLike {
        private float day;
        private float night;
        private float eve;
        private float morn;

        public float getDay() {
            return day;
        }

        public void setDay(float day) {
            this.day = day;
        }

        public float getNight() {
            return night;
        }

        public void setNight(float night) {
            this.night = night;
        }

        public float getEve() {
            return eve;
        }

        public void setEve(float eve) {
            this.eve = eve;
        }

        public float getMorn() {
            return morn;
        }

        public void setMorn(float morn) {
            this.morn = morn;
        }
    }

    private class Weather {
        private int id;
        private String main;
        private String description;
        private String icon;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMain() {
            return main;
        }

        public void setMain(String main) {
            this.main = main;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }
    }
    public String getDateFromUTCTimestamp(long mTimestamp) {
        String mDateFormate = "dd MMM yyyy";
        String date = null;
        try {
            Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
            cal.setTimeInMillis(mTimestamp * 1000L);
            date = DateFormat.format(mDateFormate, cal.getTimeInMillis()).toString();

            SimpleDateFormat formatter = new SimpleDateFormat(mDateFormate);
            formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date value = formatter.parse(date);

            SimpleDateFormat dateFormatter = new SimpleDateFormat(mDateFormate);
            dateFormatter.setTimeZone(TimeZone.getDefault());
            date = dateFormatter.format(value);
            return date;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }
}
