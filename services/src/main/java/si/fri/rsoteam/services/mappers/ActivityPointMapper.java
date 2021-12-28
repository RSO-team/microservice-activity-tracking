package si.fri.rsoteam.services.mappers;

import si.fri.rsoteam.dtos.ActivityPointDto;
import si.fri.rsoteam.models.entities.ActivityPointEntity;

public class ActivityPointMapper {
    public static ActivityPointDto entityToDto(ActivityPointEntity ue) {
        ActivityPointDto dto = new ActivityPointDto();
        dto.id = ue.getId();
        dto.longitude = ue.getLongitude();
        dto.latitude = ue.getLatitude();
        dto.timestamp = ue.getTimestamp();
        dto.exercise_id = ue.getExercise_id();
        dto.user_id = ue.getUser_id();

        return dto;
    }

    public static ActivityPointEntity dtoToEntity(ActivityPointDto userDto) {
        ActivityPointEntity entity = new ActivityPointEntity();
        entity.setId(userDto.id);
        entity.setExercise_id(userDto.exercise_id);
        entity.setUser_id(userDto.user_id);
        entity.setTimestamp(userDto.timestamp);
        entity.setLatitude(userDto.latitude);
        entity.setLongitude(userDto.longitude);

        return entity;
    }
}
