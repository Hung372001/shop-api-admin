package com.backend.shopapi.service;

import com.backend.shopapi.dto.GroupCategoriesResponse;
import com.backend.shopapi.dto.ParentCategoriesDto;
import com.backend.shopapi.dto.ParentCategoriesResponse;
import com.backend.shopapi.dto.UpdateParentCategoryDto;
import com.backend.shopapi.model.GroupCategories;
import com.backend.shopapi.model.ParentsCategoris;

import java.util.List;

public interface ParentCategoriesServices {
    ParentsCategoris createdParentCategory(int groupCategory,ParentsCategoris parentsCategoris);
    List<ParentsCategoris> findByGroupCategories(GroupCategories groupCategories);
    ParentCategoriesResponse getAllParentsCategories(String search, int page, int size);
    ParentCategoriesDto getParentCategoryById(int id);
    ParentsCategoris updateParentCategory(int id , UpdateParentCategoryDto updateParentCategoryDto);
    void deleteGroupCategories(int id);
}
