package com.VisualizeMap;


public class Road {
    private City city1;
    private City city2;
    private int lanes;

    public Road(City city1, City city2, int lanes) {
        this.city1 = city1;
        this.city2 = city2;
        this.lanes = lanes;
    }

    public City getCity1() {
        return city1;
    }

    public City getCity2() {
        return city2;
    }

    public int getLanes() {
        return lanes;
    }
}
