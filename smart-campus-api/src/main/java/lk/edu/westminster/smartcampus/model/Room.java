package lk.edu.westminster.smartcampus.model;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private String id;
    private String name;
    private int capacity;
    private String floor; // අලුතින් එක් කළා
    private List<String> sensorIds = new ArrayList<>();

    public Room() {}

    // නව Constructor එක
    public Room(String id, String name, int capacity, String floor) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.floor = floor;
    }

    // Getters and Setters (floor සඳහා අලුතින්)
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }

    public String getFloor() { return floor; }
    public void setFloor(String floor) { this.floor = floor; }
    
    public List<String> getSensorIds() { return sensorIds; }
    public void setSensorIds(List<String> sensorIds) { this.sensorIds = sensorIds; }
}