/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.edu.westminster.smartcampus.resource;

import lk.edu.westminster.smartcampus.model.Room;
import lk.edu.westminster.smartcampus.repository.DataStore;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response; // මෙය අමතක කරන්න එපා
import java.util.List;

@Path("/rooms")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RoomResource {

    // GET /api/v1/rooms - සියලුම කාමර ලැයිස්තුව ලබා ගැනීමට
    @GET
    public List<Room> getAllRooms() {
        return DataStore.getInstance().getRooms();
    }

    // POST /api/v1/rooms - නව කාමරයක් නිර්මාණය කිරීමට
    @POST
    public Room addRoom(Room room) {
        DataStore.getInstance().getRooms().add(room);
        return room;
    }

    // GET /api/v1/rooms/{roomId} - නිශ්චිත කාමරයක් සොයා ගැනීමට
    @GET
    @Path("/{roomId}")
    public Room getRoom(@PathParam("roomId") String roomId) {
        for (Room r : DataStore.getInstance().getRooms()) {
            if (r.getId().equals(roomId)) {
                return r;
            }
        }
        throw new NotFoundException("Room with ID " + roomId + " not found.");
    }

    // --- DELETE /api/v1/rooms/{roomId} - කාමරයක් මැකීමට ---
    @DELETE
    @Path("/{roomId}")
    public Response deleteRoom(@PathParam("roomId") String roomId) {
        boolean removed = DataStore.getInstance().deleteRoom(roomId);
        
        if (removed) {
            // සාර්ථකව මැකුවා නම් 200 OK
            return Response.ok("Room with ID " + roomId + " deleted successfully.").build();
        } else {
            // දත්ත හමු නොවූයේ නම් 404 Not Found
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("Room with ID " + roomId + " not found.")
                           .build();
        }
    }
}
