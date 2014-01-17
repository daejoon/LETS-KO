package dd2.local.entity;

import javax.persistence.*;

/**
 * Created by kdj on 2014. 1. 18..
 */
@javax.persistence.Table(name = "SAMPLE")
@Entity
public class SampleEntity {
    private Long id;
    private String name;
    private Long age;
    private String description;

    public SampleEntity() {
    }

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "NAME", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "AGE", nullable = false)
    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    @Column(name = "DESCRIPTION", nullable = true)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
