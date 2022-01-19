package com.github.ybqdren.repository;

import org.github.ybqdren.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Wen(Joan) Zhao
 * @time 2022/1/18 15:44
 * @package org.github.ybqdren.Repository
 * @description
 **/
public interface UserPagingAndSortingRepository extends PagingAndSortingRepository<User,Long> {
}
