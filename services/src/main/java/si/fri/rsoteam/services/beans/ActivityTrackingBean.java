package si.fri.rsoteam.services.beans;

import si.fri.rsoteam.dtos.ActivityPointDto;
import si.fri.rsoteam.models.entities.ActivityPointEntity;
import si.fri.rsoteam.services.mappers.ActivityPointMapper;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RequestScoped
public class ActivityTrackingBean {
    private Logger log = Logger.getLogger(ActivityTrackingBean.class.getName());

    @PersistenceContext
    private EntityManager em;

    public List<ActivityPointDto> getList() {
        TypedQuery<ActivityPointEntity> query = em.createNamedQuery("ActivityPoint.getAll", ActivityPointEntity.class);
        return query.getResultList().stream().map(ActivityPointMapper::entityToDto).collect(Collectors.toList());
    }

    public ActivityPointDto get(Integer id) {
        ActivityPointEntity entity = em.find(ActivityPointEntity.class, id);
        return ActivityPointMapper.entityToDto(entity);
    }

    public ActivityPointDto create(ActivityPointDto user) {
        this.beginTx();
        ActivityPointEntity entity = ActivityPointMapper.dtoToEntity(user);
        em.persist(entity);
        this.commitTx();
        return ActivityPointMapper.entityToDto(entity);
    }

    public ActivityPointDto update(ActivityPointDto user, Integer id){
        this.beginTx();

        ActivityPointEntity entity = em.find(ActivityPointEntity.class, id);
        entity.setUser_id(user.user_id);
        entity.setExercise_id(user.exercise_id);
        entity.setTimestamp(user.timestamp);
        entity.setLatitude(user.latitude);
        entity.setLongitude(user.longitude);
        em.persist(entity);

        this.commitTx();

        return ActivityPointMapper.entityToDto(entity);
    }

    public void delete(Integer id) {
        ActivityPointEntity entity = em.find(ActivityPointEntity.class, id);
        if (entity != null) {
            this.beginTx();
            em.remove(entity);
            this.commitTx();
        } else {
            throw new NotFoundException("Obj not found");
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
