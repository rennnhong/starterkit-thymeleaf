package indi.rennnhong.staterkit.module.student.model.entity;

import indi.rennnhong.staterkit.persistence.BaseEntity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@SQLDelete(sql="update Student set deleted = 1 where id = ?")
@Where(clause = "deleted = 0")
public class Student extends BaseEntity<String> {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Integer age;
    //邏輯删除（0 未删除、1 删除）
    private Integer deleted = 0;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}
