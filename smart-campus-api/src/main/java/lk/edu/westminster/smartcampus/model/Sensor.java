package lk.edu.westminster.smartcampus.model;

public class Sensor {
    package lk.edu.westminster.smartcampus.model;

import java.util.ArrayList;
import java.util.List;

public class Sensor {
    private String id;
    private String type; // e.g., TEMPERATURE, CO2
    private List<Reading> readings = new ArrayList<>();

    public Sensor() {}
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public List<Reading> getReadings() { return readings; }
}
}
