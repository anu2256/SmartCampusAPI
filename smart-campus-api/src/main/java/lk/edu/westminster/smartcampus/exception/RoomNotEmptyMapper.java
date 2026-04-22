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
public class RoomNotEmptyMapper implements ExceptionMapper<RoomNotEmptyException> {
    @Override
    public Response toResponse(RoomNotEmptyException exception) {
        // අර කලින් හදපු ErrorMessage පන්තිය මෙතනදී පාවිච්චි කරන්න
        ErrorMessage msg = new ErrorMessage(exception.getMessage(), 409);
        
        return Response.status(Response.Status.CONFLICT)
                       .entity(msg)
                       .type(MediaType.APPLICATION_JSON)
                       .build();
    }
}
