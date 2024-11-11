package com.backend.shopapi.repository;

import com.backend.shopapi.model.GroupCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface GroupCategoriesRepository extends JpaRepository<GroupCategories, Integer> {
    Page<GroupCategories> findByNameContaining(String name, Pageable pageable);

}
