/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.edu.westminster.smartcampus;

import lk.edu.westminster.smartcampus.resource.RoomResource;
import lk.edu.westminster.smartcampus.resource.SensorResource;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api/v1")
public class MainApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();
        
        // මෙහි ඔබේ සියලුම Resource පන්ති එකතු කරන්න
        resources.add(SensorResource.class); 
        resources.add(RoomResource.class); 
        
        return resources;
    }
}
