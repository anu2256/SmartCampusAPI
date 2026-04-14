package lk.edu.westminster;

public class Room {
    package lk.edu.westminster.smartcampus.model;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private String id;
    private String name;
    private List<Sensor> sensors = new ArrayList<>();

    // Constructor, Getters සහ Setters අවශ්‍යයි
    public Room() {}
    public Room(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<Sensor> getSensors() { return sensors; }
    public void setSensors(List<Sensor> sensors) { this.sensors = sensors; }

}
