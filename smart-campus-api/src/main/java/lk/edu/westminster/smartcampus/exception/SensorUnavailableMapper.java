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
public class SensorUnavailableMapper implements ExceptionMapper<SensorUnavailableException> {
    @Override
    public Response toResponse(SensorUnavailableException exception) {
        ErrorMessage msg = new ErrorMessage(exception.getMessage(), 403);
        
        return Response.status(Response.Status.FORBIDDEN) // 403 Forbidden
                       .entity(msg)
                       .type(MediaType.APPLICATION_JSON)
                       .build();
    }
}

