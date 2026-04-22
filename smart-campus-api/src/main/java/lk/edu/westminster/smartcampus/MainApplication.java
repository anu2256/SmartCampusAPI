/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.edu.westminster.smartcampus;

import lk.edu.westminster.smartcampus.resource.SensorResource; // ඔබේ SensorResource එක Import කරන්න
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api/v1")
public class MainApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();
        // මෙතැනදී ඔබේ Resource පන්ති එකතු කරන්න
        resources.add(SensorResource.class); 
        // ඔබට RoomResource එකක් තිබේ නම්, එයත් මෙසේ එක් කරන්න:
        // resources.add(RoomResource.class); 
        return resources;
    }
}
