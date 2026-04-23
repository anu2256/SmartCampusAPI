/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.edu.westminster.smartcampus.resource;

import lk.edu.westminster.smartcampus.model.Sensor;
import lk.edu.westminster.smartcampus.model.Room;
import lk.edu.westminster.smartcampus.repository.DataStore;
import lk.edu.westminster.smartcampus.exception.LinkedResourceNotFoundException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/sensors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SensorResource {

    // 1. GET - Filtering සමඟ
    @GET
    public List<Sensor> getSensors(@QueryParam("type") String type) {
        List<Sensor> allSensors = DataStore.getInstance().getSensors();
        
        if (type != null && !type.isEmpty()) {
            List<Sensor> filtered = new ArrayList<>();
            for (Sensor s : allSensors) {
                if (s.getType().equalsIgnoreCase(type)) {
                    filtered.add(s);
                }
            }
            return filtered;
        }
        return allSensors;
    }

    // 2. POST - Room Validation සහ Exception Handling සමඟ
    @POST
    public Response addSensor(Sensor sensor) {
        
        if (sensor.getId() == null || sensor.getId().isEmpty()) {
            int newId = DataStore.getInstance().getSensors().size() + 1;
            sensor.setId(String.valueOf(newId));
        }

        Room room = null;
        for (Room r : DataStore.getInstance().getRooms()) {
            if (r.getId().equalsIgnoreCase(sensor.getRoomId())) {
                room = r;
                break;
            }
        }

        if (room == null) {
            throw new LinkedResourceNotFoundException("Error: Room ID " + sensor.getRoomId() + " not found.");
        }

        DataStore.getInstance().getSensors().add(sensor);
        room.getSensorIds().add(sensor.getId());
        
        return Response.status(Response.Status.CREATED).entity(sensor).build();
    }

    // 3. PUT - සෙන්සරයේ Status එක Update කිරීම (Maintenance Mode සඳහා)
    @PUT
    @Path("/{id}")
    public Response updateSensorStatus(@PathParam("id") String id, Sensor updatedSensor) {
        // සෙන්සරය හොයාගන්න
        Sensor sensorToUpdate = null;
        for (Sensor s : DataStore.getInstance().getSensors()) {
            if (s.getId().equalsIgnoreCase(id)) {
                sensorToUpdate = s;
                break;
            }
        }

        // සෙන්සරය නැත්නම් 404 Error එකක් දෙන්න
        if (sensorToUpdate == null) {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("{\"error\": \"Sensor with ID " + id + " not found.\"}")
                           .build();
        }

        // Status එක Update කරන්න
        sensorToUpdate.setStatus(updatedSensor.getStatus());

        return Response.status(Response.Status.OK).entity(sensorToUpdate).build();
    }

    // 4. Sub-resource locator (SensorReading සඳහා)
    @Path("/{id}/readings")
    public SensorReadingResource getReadings(@PathParam("id") String id) {
        boolean exists = DataStore.getInstance().getSensors().stream()
                .anyMatch(s -> s.getId().equalsIgnoreCase(id));

        if (!exists) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        return new SensorReadingResource(id);
    }
}
