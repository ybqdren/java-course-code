package entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Wen(Joan) Zhao
 * @time 2022/1/3 10:33
 * @package entity
 * @description  用户的实体
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SysUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotNull
    private String username; // 权限的名称

    private String password;

    private String realName;

    private Boolean enable;

    @ManyToMany(targetEntity = SYsRole.class,fetch = FetchType.EAGER)
    private Set<SYsRole> roles; // 用户和角色是多对多的关系

    // 获取当前用户的角色合集，通过角色集合获得当前用户的权限集合
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new HashSet<>();
        roles.forEach(role -> {
            role.getAuthorities().forEach(authority ->{
                authorities.add(new SimpleGrantedAuthority(authority.getValue()));
            });
        });

        return authorities;
    }


    public SysUser(String username, String password, String realName, Boolean enable, Set<SYsRole> roles) {
        this.username = username;
        this.password = password;
        this.realName = realName;
        this.enable = enable;
        this.roles = roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
