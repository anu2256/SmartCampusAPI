/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.edu.westminster.smartcampus.model;

/**
 *
 * @author USER
 */
public class SensorReading {
    private String id;
    private String sensorId; // කුමන sensor එකෙන්ද මේ දත්ත ආවේ?
    private double value;    // අගය (Value)
    private long timestamp;  // දත්ත ලැබුණු වෙලාව

    // Constructor
    public SensorReading(String id, String sensorId, double value, long timestamp) {
        this.id = id;
        this.sensorId = sensorId;
        this.value = value;
        this.timestamp = timestamp;
    }

    // Getters සහ Setters අනිවාර්යයෙන්ම දාන්න
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getSensorId() { return sensorId; }
    public void setSensorId(String sensorId) { this.sensorId = sensorId; }
    public double getValue() { return value; }
    public void setValue(double value) { this.value = value; }
    public long getTimestamp() { return timestamp; }
    public void setTimestamp(long timestamp) { this.timestamp = timestamp; }
}

