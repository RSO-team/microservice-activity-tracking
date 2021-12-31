package si.fri.rsoteam.services.mappers;

import si.fri.rsoteam.entities.ActivityEntity;
import si.fri.rsoteam.lib.dtos.ActivityDto;

public class ActivityMapper {
    public static ActivityDto entityToDto(ActivityEntity et) {
        ActivityDto activityDto = new ActivityDto();
        activityDto.id = et.getId();
        activityDto.userId = et.getUserId();
        activityDto.experience= et.getExperience();
        activityDto.name= et.getName();

        return activityDto;
    }

    public static ActivityEntity dtoToEntity(ActivityDto activityDto) {
        ActivityEntity activityEntity = new ActivityEntity();
        activityEntity.setName(activityDto.name);
        activityEntity.setExperience(activityDto.experience);
        activityEntity.setUserId(activityDto.userId);
        return  activityEntity;
    }
}
