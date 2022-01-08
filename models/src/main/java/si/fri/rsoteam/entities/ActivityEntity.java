package si.fri.rsoteam.entities;


import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "activity")
@NamedQuery(name = "Activity.getAll", query = "SELECT e from ActivityEntity e")
@NamedQuery(name = "Activity.deleteForUser", query = "DELETE FROM ActivityEntity ae WHERE ae.userId = :userId")
public class ActivityEntity implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Integer id;

    @Size(min = 3, max = 20)
    private String name;

    private Integer userId;

    private Integer experience;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }
}