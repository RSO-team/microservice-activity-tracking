package si.fri.rsoteam.services.beans;

import org.eclipse.microprofile.metrics.annotation.Timed;
import si.fri.rsoteam.entities.ActivityEntity;
import si.fri.rsoteam.lib.dtos.ActivityDto;
import si.fri.rsoteam.services.mappers.ActivityMapper;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.stream.Collectors;

import com.kumuluz.ee.logs.LogManager;
import com.kumuluz.ee.logs.Logger;


@RequestScoped
public class ActivityBean {
    private Logger log = LogManager.getLogger(ActivityBean.class.getName());

    @Inject
    private EntityManager em;

    @Timed
    public ActivityDto getActivity(Integer id) {
        return ActivityMapper.entityToDto(em.find(ActivityEntity.class, id));
    }

    @Timed
    public List<ActivityDto> getAllActivity() {
        return em.createNamedQuery("Activity.getAll", ActivityEntity.class)
                .getResultList()
                .stream()
                .map(ActivityMapper::entityToDto)
                .collect(Collectors.toList());
    }

    public ActivityDto createActivity(ActivityDto activityDto) {
        ActivityEntity activityEntity = ActivityMapper.dtoToEntity(activityDto);
        this.beginTx();
        em.persist(activityEntity);
        this.commitTx();

        return ActivityMapper.entityToDto(activityEntity);
    }

    public ActivityDto updateActivity(ActivityDto activityDto, Integer id) {
        this.beginTx();

        ActivityEntity activityEntity = em.find(ActivityEntity.class, id);
        activityEntity.setExperience(activityDto.experience);
        activityEntity.setUserId(activityDto.userId);
        activityEntity.setName(activityDto.name);
        em.persist(activityEntity);
        this.commitTx();

        return ActivityMapper.entityToDto(activityEntity);
    }

    public void deleteActivity(Integer id) {
        ActivityEntity activityEntity = em.find(ActivityEntity.class, id);
        if (activityEntity != null) {
            this.beginTx();
            em.remove(activityEntity);
            this.commitTx();
        }
    }

    public void deleteActivitiesForUser(Integer userId) {
        beginTx();
        try {
            em.createNamedQuery("Activity.deleteForUser", TypedQuery.class)
                    .setParameter("userId", userId)
                    .executeUpdate();
            commitTx();
        } catch (Exception e) {
            rollbackTx();
        }
    }

    private void beginTx() {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
    }

    private void commitTx() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().commit();
        }
    }

    private void rollbackTx() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
    }
}
