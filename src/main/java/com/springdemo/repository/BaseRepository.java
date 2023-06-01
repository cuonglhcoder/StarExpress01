package com.springdemo.repository;

import org.springframework.data.annotation.Id;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
public interface BaseRepository<T,ID> extends
        CrudRepository<T, ID>,
        PagingAndSortingRepository<T, ID>,
        JpaSpecificationExecutor<T>{
}
