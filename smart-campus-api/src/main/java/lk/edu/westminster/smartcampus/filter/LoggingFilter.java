/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.edu.westminster.smartcampus.filter;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.logging.Logger;

@Provider // මෙය අනිවාර්යයි, එවිට JAX-RS මෙය ස්වයංක්‍රීයව හඳුනා ගනී
public class LoggingFilter implements ContainerRequestFilter, ContainerResponseFilter {
    
    // Logger එකක් නිර්මාණය කිරීම
    private static final Logger LOGGER = Logger.getLogger(LoggingFilter.class.getName());

    // Request එක එන විට ක්‍රියාත්මක වේ
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        LOGGER.info("Incoming Request: " + requestContext.getMethod() + " " + requestContext.getUriInfo().getPath());
    }

    // Response එක යවන විට ක්‍රියාත්මක වේ
    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        LOGGER.info("Outgoing Response Status: " + responseContext.getStatus());
    }
}

