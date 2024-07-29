package com.VisualizeMap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.List;
@SpringBootApplication
public class VisualizeMapApplication{


		public static void main(String[] args) {
			MapGenerator mapGenerator = new MapGenerator();
			List<City> cities = mapGenerator.generateCities();
			mapGenerator.printCities(cities);

			RoadNetworkGenerator roadNetworkGenerator = new RoadNetworkGenerator();
			List<Road> roads = roadNetworkGenerator.generateRoads(cities);

			MapVisualizer mapVisualizer = new MapVisualizer();
			mapVisualizer.visualizeMap(cities, roads);
		}
	}
