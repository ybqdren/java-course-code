package com.github.ybqdren;

import com.github.ybqdren.entity.User;
import com.github.ybqdren.entity.UserInfo;
import com.github.ybqdren.repository.UserInfoJpaNativeSQLRepository;
import com.github.ybqdren.repository.UserJpaNativeSQLRepository;
import com.github.ybqdren.repository.UserJpaRepository;
import com.github.ybqdren.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @author Wen(Joan) Zhao
 * @time 2022/1/19 10:59
 * @package com.github.ybqdren
 * @description
 * 单元测试参考：
 *      https://blog.csdn.net/HD243608836/article/details/122239150 < Spring Boot Test单元测试——Junit4、Junit5区别与@ExtendWith不识别生效问题解析 >
 **/


//@ExtendWith(SpringExtension.class) spring boot 2.1.x 之后已经整合
@SpringBootTest // 标记该类为 spring boot 单元测试类，并加载项目的 applicationContext 上下文环境
public class SpringDataJpaTestApplication {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserJpaRepository userJpaRepository;

    @Autowired
    UserJpaNativeSQLRepository userJpaNativeSQLRepository;

    @Autowired
    UserInfoJpaNativeSQLRepository userInfoJpaNativeSQLRepository;

    /** 注解查询  使用原始的 sql */
    @Test
    void userInfoFindJpaNativeSqlRe(){
        UserInfo userInfo = UserInfo.builder()
                .nikaName("ybqdren")
                .firstName("wen")
                .lastName("zhao")
                .emailAddress("withzhaowen@126.com")
                .build();

        if (userInfoJpaNativeSQLRepository.findByEmailAddress("withzhaowen@126.com") == null) {
            userInfoJpaNativeSQLRepository.save(userInfo);
        }

        /** UserInfo(id=1, nikaName=ybqdren, firstName=wen, lastName=zhao, emailAddress=withzhaowen@126.com)*/
        List<UserInfo> userInfos = userInfoJpaNativeSQLRepository.findByFirstName("wen","last_name");
        if(userInfos.size() > 0){
            System.out.println(userInfos.get(0).toString());
        }
    }

    /** 注解查询  使用原始的 sql */
    @Test
    void userFindJpaNativeSqlRe(){
        /** User(id=2, name=ybqdren, email=withzhaowen@126.com) */
        User user = userJpaNativeSQLRepository.findByEmail("withzhaowen@126.com");
        System.out.println(user.toString());
    }

    /** 注解查询 */
    @Test
    void userFindJpaRe(){
        User user = userJpaRepository.findByEmail("withzhaowen@126.com");
        System.out.println(user.toString());

        List<User> users = userJpaRepository.findByNameEndingWith("i");
        System.out.println(users.get(0).toString());
    }

    /** 分页查询 */
    @Test
    void userFindPaging(){
/*        // 这里的"recordNo"是实体类的主键，记住一定要是实体类的属性，而不能是数据库的字段 https://www.cnblogs.com/chuangqi/p/11261482.html
        Sort sort = new Sort(Sort.Direction.DESC, "recordNo");
        Pageable pageable = new PageRequest(1, 6, sort);
        userPagingAndSortingRepository.findByNameLike("ybqdren",pageable);*/
    }

    /** curd 查询 */
    @Test
    void userFind(){
        /**
         [User(id=8, name=jiushi, email=jiushi@126.com),
         User(id=7, name=daqi, email=daqi@126.com),
         User(id=5, name=lisi, email=lisi@126.com)]
         */
        List<User> users = userRepository.findByNameEndingWithOrderByIdDesc("i");
        System.out.println(users.toString());

        /** [User(id=2, name=ybqdren, email=withzhaowen@126.com)] */
        users = userRepository.findByNameLike("ybqdre%");
        System.out.println(users.toString());

        /** [User(id=2, name=ybqdren, email=withzhaowen@126.com)] */
        users = userRepository.findByEmailContaining("%@126%");
        System.out.println(users.toString());

        /** 1 */
        Long count = userRepository.countByName("ybqdren");
        System.out.println(count.toString());
    }


    /**
     User(id=2, name=ybqdren, email=withzhaowen@126.com)
     */
    @Test
    void contextLoads(){
        User user = User.builder()
                .name("ybqdren")
                .email("withzhaowen@126.com")
                .id(2L)
                .build();

        if(!userRepository.findById(user.getId()).isPresent()){
            userRepository.save(user);
        }

        List<User> users = userRepository.findByNameAndId("ybqdren", 2L);
        System.out.println(users.get(0));
    }
}
