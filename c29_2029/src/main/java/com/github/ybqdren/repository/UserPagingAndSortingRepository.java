package com.github.ybqdren.repository;


import com.github.ybqdren.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * @author Wen(Joan) Zhao
 * @time 2022/1/18 15:44
 * @package org.github.ybqdren.Repository
 * @description
 *
 *  - Page 知道可用的元素和页面的总数，可排序
 *         > 通过基础框架里面触发计数查询来计算总数，代价高昂
 *           相当于 每次用到 Pageable 时都会执行一条 count 语句
 *
 *  - Slice 不需要知道页面的总数和可用的元素总数
 *         > 只知道是否有下一个 Slice 可用，不会执行 count 语句
 *           所以当查询较大的结果集时，只知道数据是足够的就可以了，就不用关心一共有多少页
 **/

public interface UserPagingAndSortingRepository extends PagingAndSortingRepository<User,Long> {

    /** 动态将分页添加到静态定义的查询中 */

    Page<User> findByNameLike(String userName , Pageable pageable);

    Slice<User> findByNamelike(String userName , Pageable pageable);

    List<User> findByNameLike(String userName, Sort sort);
}
