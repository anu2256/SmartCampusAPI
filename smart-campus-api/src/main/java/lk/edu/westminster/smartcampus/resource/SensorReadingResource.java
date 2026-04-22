/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.edu.westminster.smartcampus.resource;

import lk.edu.westminster.smartcampus.model.Sensor; // අනිවාර්යයෙන්ම මේ import එක එකතු කරන්න
import lk.edu.westminster.smartcampus.model.SensorReading;
import lk.edu.westminster.smartcampus.repository.DataStore;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

public class SensorReadingResource {
    private String sensorId;

    public SensorReadingResource(String sensorId) {
        this.sensorId = sensorId;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<SensorReading> getReadings() {
        return DataStore.getInstance().getReadings().stream()
                .filter(r -> String.valueOf(r.getSensorId()).equals(sensorId))
                .collect(Collectors.toList());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public SensorReading addReading(SensorReading reading) {
        // 1. Reading එකට අදාළ Sensor ID එක දීම
        reading.setSensorId(sensorId);
        
        // 2. DataStore එකට Reading එක එකතු කිරීම
        DataStore.getInstance().getReadings().add(reading);
        
        // 3. Side Effect: අදාළ Sensor එකේ currentValue එක update කිරීම
        Sensor parentSensor = DataStore.getInstance().getSensors().stream()
                .filter(s -> String.valueOf(s.getId()).equals(sensorId))
                .findFirst()
                .orElse(null);
        
        if (parentSensor != null) {
            parentSensor.setCurrentValue(reading.getValue());
        }
        
        return reading;
    }
}