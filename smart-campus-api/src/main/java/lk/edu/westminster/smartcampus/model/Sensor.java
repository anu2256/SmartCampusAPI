package lk.edu.westminster.smartcampus.model;

import java.util.ArrayList;
import java.util.List;

public class Sensor {
    private String id;
    private String type; 
    private String roomId;
    private double currentValue;
    private String status; // අලුතින් එකතු කළා
    private List<SensorReading> sensorReadings = new ArrayList<>();

    public Sensor() {}

    public Sensor(int id, String type, String roomId) {
        this.id = String.valueOf(id);
        this.type = type;
        this.roomId = roomId;
        this.status = "ACTIVE"; // default status එකක් ලෙස ACTIVE දාමු
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    
    public String getRoomId() { return roomId; }
    public void setRoomId(String roomId) { this.roomId = roomId; }

    public double getCurrentValue() { return currentValue; }
    public void setCurrentValue(double currentValue) { this.currentValue = currentValue; }
    
    // status සඳහා අලුතින් එකතු කළ getter සහ setter
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public List<SensorReading> getReadings() { return sensorReadings; }
    public void setReadings(List<SensorReading> sensorReadings) { this.sensorReadings = sensorReadings; }
}

