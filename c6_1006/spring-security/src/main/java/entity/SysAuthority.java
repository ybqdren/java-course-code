package entity;

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
 * @time 2022/1/3 10:34
 * @package entity
 * @description 权限实体
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SysAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username; // 权限名称

    private String value; // 权限值

}
