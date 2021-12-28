package si.fri.rsoteam.dtos;

import java.time.Instant;

public class ActivityPointDto {
    public Integer id;
    public Instant timestamp;
    public Integer exercise_id;
    public Integer user_id;
    public Double latitude;
    public Double longitude;

    public ActivityPointDto() {
    }
}
