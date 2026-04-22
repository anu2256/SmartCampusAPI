/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.edu.westminster.smartcampus.exception;

public class RoomNotEmptyException extends RuntimeException {
    
    // දෝෂ පණිවිඩය (message) යැවීම සඳහා constructor එකක්
    public RoomNotEmptyException(String message) {
        super(message);
    }
}
