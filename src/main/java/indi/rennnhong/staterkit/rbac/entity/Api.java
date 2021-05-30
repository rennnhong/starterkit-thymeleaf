package indi.rennnhong.staterkit.rbac.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import indi.rennnhong.staterkit.common.persistence.AuditableEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "SysApi")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class Api extends AuditableEntity<String> {

    @Column(nullable = false)
    String url;

    @Column(nullable = false)
    String httpMethod;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Api{");
        sb.append("url='").append(url).append('\'');
        sb.append(", httpMethod='").append(httpMethod).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
