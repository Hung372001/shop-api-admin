package com.backend.shopapi.dto;

import com.backend.shopapi.model.GroupCategories;

public class ParentCategoriesDto {
    private int id;
    private String name;
    private GroupCategoriesSimpleDto groupCategories; // Use the simple DTO

    public ParentCategoriesDto() {}

    public ParentCategoriesDto(int id, String name, GroupCategoriesSimpleDto groupCategories) {
        this.id = id;
        this.groupCategories = groupCategories;
        this.name = name;
    }

    // Getters and Setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GroupCategoriesSimpleDto getGroupCategories() {
        return groupCategories;
    }

    public void setGroupCategories(GroupCategoriesSimpleDto groupCategories) {
        this.groupCategories = groupCategories;
    }
}