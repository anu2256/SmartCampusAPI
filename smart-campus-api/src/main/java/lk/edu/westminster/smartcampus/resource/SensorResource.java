/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.edu.westminster.smartcampus.resource;

import lk.edu.westminster.smartcampus.model.Sensor;
import lk.edu.westminster.smartcampus.repository.DataStore;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
/**
 *
 * @author USER
 */
@Path("/sensors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SensorResource {

    @GET
    public List<Sensor> getAllSensors() {
        // Singleton 
        return DataStore.getInstance().getSensors();
    }

    @POST
    public Sensor addSensor(Sensor sensor) {
        // Singleton එකට දත්ත දාන විදිය
        DataStore.getInstance().getSensors().add(sensor);
        return sensor;
    }
}
