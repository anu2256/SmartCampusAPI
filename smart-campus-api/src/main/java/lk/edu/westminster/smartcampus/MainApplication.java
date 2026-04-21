/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.edu.westminster.smartcampus;

/**
 *
 * @author USER
 */


import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/api/v1") // ඔයාගේ URL එකේ මුල මෙතනින් හැදෙනවා
public class MainApplication extends Application {
    // මේක දාපුවාම Jersey එක ඔටෝම ඔයාගේ Resource classes හොයාගන්නවා
}
