/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.edu.westminster.smartcampus.resource;

import lk.edu.westminster.smartcampus.model.Sensor;
import lk.edu.westminster.smartcampus.model.Room;
import lk.edu.westminster.smartcampus.repository.DataStore;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.WebApplicationException;
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

    // 2. POST - Room Validation සමඟ
    @POST
    public Response addSensor(Sensor sensor) {
        Room room = null;
        for (Room r : DataStore.getInstance().getRooms()) {
            if (r.getId().equalsIgnoreCase(sensor.getRoomId())) {
                room = r;
                break;
            }
        }

        if (room == null) {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("Error: Room ID " + sensor.getRoomId() + " not found.")
                           .build();
        }

        DataStore.getInstance().getSensors().add(sensor);
        room.getSensorIds().add(sensor.getId());
        
        return Response.status(Response.Status.CREATED).entity(sensor).build();
    }

    // 3. Sub-resource locator (SensorReading සඳහා)
    @Path("/{id}/readings")
    public SensorReadingResource getReadings(@PathParam("id") String id) {
        // Validation: ඇත්තටම මේ ID එකට අදාළ Sensor එකක් තියෙනවාද?
        boolean exists = DataStore.getInstance().getSensors().stream()
                .anyMatch(s -> s.getId().equalsIgnoreCase(id));

        if (!exists) {
            // Sensor එක නැත්නම් 404 Error එකක් දෙන්න
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        // Sensor එක තියෙනවා නම්, එම ID එක සමඟ SensorReadingResource එකට යොමු කරන්න
        return new SensorReadingResource(id);
    }
}
