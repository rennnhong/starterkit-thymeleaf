package indi.rennnhong.staterkit.rbac.entity;

import indi.rennnhong.staterkit.common.persistence.AuditableEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "SysRole")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role extends AuditableEntity<String> {


    @Column(nullable = false)
    String name;

    @Column(nullable = false, unique = true)
    String code;


    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
    Set<User> users;

    @ElementCollection
    @CollectionTable(name = "sysRolePermission")
    Set<RolePermission> rolePermissions;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Role{");
        sb.append("name='").append(name).append('\'');
        sb.append(", code='").append(code).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
