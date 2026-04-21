package lk.edu.westminster.smartcampus.model;

import java.util.ArrayList;
import java.util.List;

public class Sensor {
    private String id;
    private String type; 
    
    // Variable එකේ නම පිරිසිදු කළා (sensorReadings)
    private List<SensorReading> sensorReadings = new ArrayList<>();

    public Sensor() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    
    // දැන් මෙතන හරියටම අර variable එකම return කරනවා
    public List<SensorReading> getReadings() { return sensorReadings; }
    
    public void setReadings(List<SensorReading> sensorReadings) { this.sensorReadings = sensorReadings; }
}