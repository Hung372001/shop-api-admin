package com.backend.shopapi.repository;

import com.backend.shopapi.model.GroupCategories;
import com.backend.shopapi.model.ParentsCategoris;
import com.backend.shopapi.service.ParentCategoriesServices;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParentCategoriesRepository extends JpaRepository<ParentsCategoris, Integer> {
    List<ParentsCategoris> findByGroupCategories(GroupCategories groupCategories);
    Page<ParentsCategoris> findByNameContaining(String name, Pageable pageable);

}
