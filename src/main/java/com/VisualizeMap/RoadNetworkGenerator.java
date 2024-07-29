package com.VisualizeMap;
import java.util.*;

public class RoadNetworkGenerator {
    private static final int MAX_NATIONAL_HIGHWAYS = 4;
    private Random random = new Random();

    public List<Road> generateRoads(List<City> cities) {
        List<Road> roads = new ArrayList<>();
        Map<City, Set<Road>> cityRoadMap = new HashMap<>();

        for (City city : cities) {
            cityRoadMap.put(city, new HashSet<>());
        }

        for (City city : cities) {
            // Add National Highways
            addRoads(city, cities, roads, cityRoadMap, 4, MAX_NATIONAL_HIGHWAYS);
        }

        // Add Inter-State Highways connecting to National Highways
        addSpecificLanesRoads(roads, cityRoadMap, 4, 3);

        // Add Highways connecting to Inter-State Highways
        addSpecificLanesRoads(roads, cityRoadMap, 3, 2);

        // Add Main Roads connecting to Highways
        addSpecificLanesRoads(roads, cityRoadMap, 2, 1);

        return roads;
    }

    private void addRoads(City city, List<City> cities, List<Road> roads, Map<City, Set<Road>> cityRoadMap, int lanes, int maxConnections) {
        Set<Road> cityRoads = cityRoadMap.get(city);
        int connections = 0;

        while (connections < maxConnections) {
            City otherCity = cities.get(random.nextInt(cities.size()));
            if (city.equals(otherCity)) continue;

            Road road = new Road(city, otherCity, lanes);
            if (!cityRoads.contains(road) && !cityRoadMap.get(otherCity).contains(road)) {
                roads.add(road);
                cityRoads.add(road);
                cityRoadMap.get(otherCity).add(road);
                connections++;
            }
        }
    }

    private void addSpecificLanesRoads(List<Road> roads, Map<City, Set<Road>> cityRoadMap, int fromLanes, int toLanes) {
        for (Road road : new ArrayList<>(roads)) {
            if (road.getLanes() == fromLanes) {
                addConnectingRoads(road, roads, cityRoadMap, toLanes);
            }
        }
    }

    private void addConnectingRoads(Road fromRoad, List<Road> roads, Map<City, Set<Road>> cityRoadMap, int toLanes) {
        City city1 = fromRoad.getCity1();
        City city2 = fromRoad.getCity2();

        for (City city : Arrays.asList(city1, city2)) {
            Set<Road> cityRoads = cityRoadMap.get(city);
            if (cityRoads.stream().noneMatch(road -> road.getLanes() == toLanes)) {
                City otherCity = findOtherCity(cityRoadMap, city, city1, city2);
                if (otherCity != null) {
                    Road newRoad = new Road(city, otherCity, toLanes);
                    roads.add(newRoad);
                    cityRoadMap.get(city).add(newRoad);
                    cityRoadMap.get(otherCity).add(newRoad);
                }
            }
        }
    }

    private City findOtherCity(Map<City, Set<Road>> cityRoadMap, City city, City city1, City city2) {
        for (City otherCity : cityRoadMap.keySet()) {
            if (!otherCity.equals(city) && !otherCity.equals(city1) && !otherCity.equals(city2) &&
                    cityRoadMap.get(otherCity).stream().noneMatch(road -> road.getLanes() == 1)) {
                return otherCity;
            }
        }
        return null;
    }
}
