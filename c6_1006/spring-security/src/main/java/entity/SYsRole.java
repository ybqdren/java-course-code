package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

/**
 * @author Wen(Joan) Zhao
 * @time 2022/1/3 10:33
 * @package entity
 * @description 角色的实体
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SYsRole {
    @Id
    private Long id;

    private String name;

    @ManyToMany(targetEntity = SysAuthority.class)
    private Set<SysAuthority> authorities; // 角色和权限是多对多的关系

    public SYsRole(String name, Set<SysAuthority> authorities) {
        this.name = name;
        this.authorities = authorities;
    }
}
