/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.edu.westminster.smartcampus.exception;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class LinkedResourceNotFoundMapper implements ExceptionMapper<LinkedResourceNotFoundException> {
    @Override
    public Response toResponse(LinkedResourceNotFoundException exception) {
        // දෝෂ පණිවිඩය 422 කෝඩ් එක සමඟ යැවීම
        ErrorMessage msg = new ErrorMessage(exception.getMessage(), 422);
        
        return Response.status(422) // Unprocessable Entity
                       .entity(msg)
                       .type(MediaType.APPLICATION_JSON)
                       .build();
    }
}
