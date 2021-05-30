package indi.rennnhong.staterkit.rbac.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import indi.rennnhong.staterkit.common.persistence.AuditableEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "SysUser")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
@AllArgsConstructor
@NoArgsConstructor
public class User extends AuditableEntity<String> {

    @Column
    private String userName;

    @Column(nullable = false, updatable = false, unique = true)
    private String account;

    @Column(nullable = false)
    @JsonIgnore
    private String password;


    @ElementCollection
    @CollectionTable(name = "sysUserPermission")
    Set<UserPermission> userPermissions;

    @ManyToMany
    @JoinTable(name = "sysUserRole")
    private Set<Role> roles;

    @Column
    private LocalDate birthday;

    @Column
    private String gender;

    @Column
    private String email;

    @Column
    private String phone;

    @Column
    private String city;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("userName='").append(userName).append('\'');
        sb.append(", account='").append(account).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", birthday=").append(birthday);
        sb.append(", gender='").append(gender).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

