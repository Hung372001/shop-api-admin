package com.backend.shopapi.service;

import com.backend.shopapi.dto.GroupCategoriesDto;
import com.backend.shopapi.dto.GroupCategoriesResponse;
import com.backend.shopapi.model.GroupCategories;
import com.backend.shopapi.model.ParentsCategoris;

import java.util.List;

public interface GroupsCategoriesServices {
    GroupCategories createGroupCategories(GroupCategories groupCategories);
    GroupCategories findById(int id);
    GroupCategoriesResponse getAllGroupCategories(String search, int page, int size);
    GroupCategories updateGroupCategories(int id , GroupCategories groupCategories);
    void deleteGroupCategories(int id);
}
