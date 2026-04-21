package lk.edu.westminster.smartcampus.repository;

import lk.edu.westminster.smartcampus.model.Room;
import lk.edu.westminster.smartcampus.model.Sensor;
import lk.edu.westminster.smartcampus.model.SensorReading;
import java.util.ArrayList;
import java.util.List;

public class DataStore {
    private static DataStore instance;
    private List<Room> rooms = new ArrayList<>();
    private List<Sensor> sensors = new ArrayList<>(); // අලුතින් එකතු කළා
    private List<SensorReading> readings = new ArrayList<>(); // අලුතින් එකතු කළා

    private DataStore() {}

    public static DataStore getInstance() {
        if (instance == null) {
            instance = new DataStore();
        }
        return instance;
    }

    public List<Room> getRooms() { return rooms; }
    public List<Sensor> getSensors() { return sensors; } // Getter එක
    public List<SensorReading> getReadings() { return readings; } // Getter එක
}