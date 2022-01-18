package org.github.ybqdren;

import org.github.ybqdren.config.MyConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.QueryLookupStrategy;


/**
 * @author Wen(Joan) Zhao
 * @time 2021/12/30 16:06
 * @package org.github.ybqdren
 * @description
 *
 * <h2> jpa 方法查询策略配置 </h2>
 * <p>
 *     除非有特殊需求，一般直接使用默认的策略即可。
 *
 *     - CREATE   直接根据方法名进行创建。规则是根据方法名称的构造进行尝试，一般的方法是从方法名中删除给定的一组已知前缀，
 *                并解析该方法的其余部分。如果方法名不符合规则，启动时就会报错。
 *
 *     - USE_DECLARED_QUERY  声明方式创建，即 注解方式。启动的时候会尝试找到一个声明的查询，如果没有找到就将抛出一个异常。
 *                           查询可以由某处注释或其他方法声明。
 *
 *     - CREATE_IF_NOT_FOUND 默认策略。以上两种方式的结合版。
 *                           先用声明方法进行查找，如果没有找到与方法相匹配的查询，就用 create 的方法名创建规则新建一个查询。
 * </p>
 *
 **/

@EnableJpaRepositories(queryLookupStrategy = QueryLookupStrategy.Key.CREATE_IF_NOT_FOUND) // jpa 方法查询策略配置，一般使用默认的方式即可
@Configuration(value = "org.github.ybqdren.config.*")
@SpringBootApplication //(scanBasePackages = {"org.github.ybqdren"})
public class SpringBootStartApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootStartApplication.class);
    }
}
