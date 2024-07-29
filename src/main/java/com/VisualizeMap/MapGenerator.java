package com.VisualizeMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MapGenerator {
    private static final int NUM_CITIES = 100;
    private static final double LAT_MIN = -90;
    private static final double LAT_MAX = 90;
    private static final double LON_MIN = -180;
    private static final double LON_MAX = 180;
    private Random random = new Random();

    public List<City> generateCities() {
        List<City> cities = new ArrayList<>();
        for (int i = 0; i < NUM_CITIES; i++) {
            String name = "City" + (i + 1);
            double latitude = LAT_MIN + (LAT_MAX - LAT_MIN) * random.nextDouble();
            double longitude = LON_MIN + (LON_MAX - LON_MIN) * random.nextDouble();
            cities.add(new City(name, latitude, longitude));
        }
        return cities;
    }

    public void printCities(List<City> cities) {
        for (City city : cities) {
            System.out.println(city);
        }
    }
}

