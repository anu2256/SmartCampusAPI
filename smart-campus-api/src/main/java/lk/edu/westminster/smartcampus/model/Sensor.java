package lk.edu.westminster.smartcampus.model;

import java.util.ArrayList;
import java.util.List;

public class Sensor {
    private String id;
    private String type; 
    private String roomId; // අලුතින් එක් කළා (Lab 01 තැන්පත් කිරීමට)
    private List<SensorReading> sensorReadings = new ArrayList<>();

    public Sensor() {}

    // නව Constructor එක (int, String, String) පරාමිතීන් සඳහා
    public Sensor(int id, String type, String roomId) {
        this.id = String.valueOf(id); // int එක String බවට පත් කිරීම
        this.type = type;
        this.roomId = roomId;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    
    public String getRoomId() { return roomId; }
    public void setRoomId(String roomId) { this.roomId = roomId; }
    
    public List<SensorReading> getReadings() { return sensorReadings; }
    public void setReadings(List<SensorReading> sensorReadings) { this.sensorReadings = sensorReadings; }
}
