package repository;

import entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Wen(Joan) Zhao
 * @time 2022/1/3 11:04
 * @package repository
 * @description
 **/
public interface SysUserRepository extends JpaRepository<SysUser,Long> {
    Optional<SysUser> findByUserName(String username);
}
