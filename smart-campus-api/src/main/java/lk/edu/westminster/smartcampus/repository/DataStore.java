package lk.edu.westminster.smartcampus.repository;

import lk.edu.westminster.smartcampus.model.Room;
import java.util.ArrayList;
import java.util.List;

public class DataStore {
    private static DataStore instance;
    private List<Room> rooms = new ArrayList<>();

    private DataStore() {}

    public static DataStore getInstance() {
        if (instance == null) {
            instance = new DataStore();
        }
        return instance;
    }

    public List<Room> getRooms() { return rooms; }
}