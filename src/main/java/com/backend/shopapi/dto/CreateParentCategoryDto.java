package com.backend.shopapi.dto;

public class CreateParentCategoryDto {
    private String name;
    private int groupId;

    public CreateParentCategoryDto() {}

    public CreateParentCategoryDto(String name, int groupId) {
        this.name = name;
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
}