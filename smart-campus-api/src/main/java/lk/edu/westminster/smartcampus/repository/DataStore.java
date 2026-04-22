package lk.edu.westminster.smartcampus.repository;

import lk.edu.westminster.smartcampus.model.Room;
import lk.edu.westminster.smartcampus.model.Sensor;
import lk.edu.westminster.smartcampus.model.SensorReading;
import java.util.ArrayList;
import java.util.List;

public class DataStore {
    private static DataStore instance;
    private List<Room> rooms = new ArrayList<>();
    private List<Sensor> sensors = new ArrayList<>();
    private List<SensorReading> readings = new ArrayList<>();

    // Private constructor: මෙතැනදී දත්ත මුලින්ම පිරවීම (Initialize) සිදු කරයි
    private DataStore() {
        // Rooms පිරවීම
        rooms.add(new Room("1", "Lab 01", 30, "Floor 1"));
        rooms.add(new Room("2", "Lecture Hall A", 50, "Floor 2"));

        // Sensors පිරවීම (අවශ්‍ය නම් දත්ත එක් කරන්න)
        sensors.add(new Sensor(1, "Temperature Sensor", "Lab 01"));
        sensors.add(new Sensor(2, "Humidity Sensor", "Lab 01"));
    }

    public static DataStore getInstance() {
        if (instance == null) {
            instance = new DataStore();
        }
        return instance;
    }

    public List<Room> getRooms() { return rooms; }
    public List<Sensor> getSensors() { return sensors; }
    public List<SensorReading> getReadings() { return readings; }
}