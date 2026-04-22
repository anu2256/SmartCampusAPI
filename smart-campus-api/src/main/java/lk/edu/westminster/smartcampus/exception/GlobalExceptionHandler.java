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
public class GlobalExceptionHandler implements ExceptionMapper<Throwable> {
    
    @Override
    public Response toResponse(Throwable ex) {
        // 1. මේ දෝෂය Console එකේ Print කරන්න (Developer ට බලන්න පුළුවන්)
        ex.printStackTrace(); 
        
        // 2. User ට යැවිය යුතු පණිවිඩය (Security හේතු නිසා technical details පෙන්වන්නේ නැත)
        ErrorMessage msg = new ErrorMessage("An unexpected error occurred. Please try again later.", 500);
        
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                       .entity(msg)
                       .type(MediaType.APPLICATION_JSON)
                       .build();
    }
}
