package com.VisualizeMap;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.graph.Node;
import org.graphstream.graph.Edge;

import java.util.List;
import java.util.UUID;

public class MapVisualizer {
    public void visualizeMap(List<City> cities, List<Road> roads) {
        Graph graph = new SingleGraph("City Map");

        for (City city : cities) {
            Node node = graph.addNode(city.getName());
            node.setAttribute("ui.label", city.getName());
        }

        for (Road road : roads) {
            String edgeId = UUID.randomUUID().toString();
            Edge edge = graph.addEdge(edgeId, road.getCity1().getName(), road.getCity2().getName(), true);
            edge.setAttribute("ui.label", road.getLanes() + " lanes");
            switch (road.getLanes()) {
                case 4:
                    edge.setAttribute("ui.style", "fill-color: red;");
                    break;
                case 3:
                    edge.setAttribute("ui.style", "fill-color: orange;");
                    break;
                case 2:
                    edge.setAttribute("ui.style", "fill-color: yellow;");
                    break;
                case 1:
                    edge.setAttribute("ui.style", "fill-color: green;");
                    break;
            }
        }

        graph.display();
    }
}

