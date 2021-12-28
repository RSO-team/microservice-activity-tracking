package si.fri.rsoteam.models.entities;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "activitypoint")
@NamedQuery(name = "ActivityPoint.getAll", query = "SELECT u from ActivityPointEntity u")
public class ActivityPointEntity implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Instant timestamp;

    private Integer exercise_id;

    private Integer user_id;

    private Double latitude;

    private Double longitude;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getExercise_id() {
        return exercise_id;
    }

    public void setExercise_id(Integer exercise_id) {
        this.exercise_id = exercise_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
