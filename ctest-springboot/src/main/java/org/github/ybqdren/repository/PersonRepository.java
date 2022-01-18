package org.github.ybqdren.repository;

import org.github.ybqdren.entity.Person;
import org.github.ybqdren.entity.User;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * @author Wen(Joan) Zhao
 * @time 2022/1/18 16:50
 * @package org.github.ybqdren.repository
 * @description
 *
 * <h2> 查询方式一：根据方法名的查询生成器 </h2>
 * <p> 待查询功能的方法名由查询策略（关键字）、查询字段和一些限制性条件组成。此方法对于在存储库的实体上构建约束查询很有用 </p>
 *  <p>
 *      该机制方法的前缀有:
 *          find...By
 *          read...By
 *          query...By
 *          count...By
 *          get...By
 *
 *      命名符合上面的内容，jpa 就可以分析它的其他部分（实体里面的字段）。
 *      第一个 By 作为分隔符来指示实际标准的开始，在定义实体条件的时，可以使用 And 和 Or 串联 ..
 *  </p>
 *
 *  <p>
 *      引入子句可以包含其他表达式，例如在 Distinct 要创建的查询上设置不同的标志。
 *  </p>
 *
 *  <p>
 *      按照方法名进行查询注意：
 *          - 表达式通常是可以连接的运算符的属性遍历。你可以使用组合属性表达式AND和OR。
 *            你还可以将运算关键字Between、LessThan、GreaterThan、Like作为属性表达式。
 *            受支持的操作员可能因数据存储而异，因此请参阅官方参考文档的相应部分内容。
 *
 *          - 该方法解析器支持设置一个IgnoreCase标志个别特性（例如，findByLastnameIgnoreCase(…)）或
 *            支持忽略大小写（通常是一个类型的所有属性为String的情况下，例如，findByLastnameAndFirstnameAllIgnoreCase(…)）。
 *            是否支持忽略示例可能会因存储而异，因此请参阅参考文档中的相关章节，了解特定于场景的查询方法。
 *
 *          - 可以通过OrderBy在引用属性和提供排序方向（Asc或Desc）的查询方法中附加一个子句来应用静态排序。要创建支持动态排序的查询方法来影响查询结果。
 *  </p>
 *
 **/
public interface PersonRepository extends Repository<Person, Long> {
    /** 包含 distinct 去重，or 的 SQL 语法 */
    List<Person> findDistinctPeopleByLastnameOrFirstname(String lastname,String firstname);

    List<Person> findPeopleDistinctByLastnameOrFirstname(String lastname,String firstname);

    /** 根据 lastname 字段查询忽略大小写 */
    List<Person> findByLastnameIgnoreCase(String lastname);

    /** 根据 lastname 和 firstname 查询 equal 并且忽略大小写 */
    List<Person> findByLastnameAndFirstnameAllIgnoreCase(String lastname,String firstname);

    /** 对查询结果根据 lastname 排序 */
    List<Person> findByLastnameOrderFirstnameAsc(String lastname);
    List<Person> findByLastnameOrderByFirstnameDesc(String lastname);
}
