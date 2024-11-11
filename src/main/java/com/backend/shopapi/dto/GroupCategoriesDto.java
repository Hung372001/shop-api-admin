package com.backend.shopapi.dto;

import com.backend.shopapi.model.ParentsCategoris;

import java.util.List;

public class GroupCategoriesDto {
    private int id;
    private String name;
    private List<ParentsCategoris> parentsCategorises;

    public GroupCategoriesDto() {}

    public GroupCategoriesDto(int id, String name,List<ParentsCategoris> parentsCategorises ){
        this.id = id;
        this.parentsCategorises = parentsCategorises;
        this.name = name;


    }

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

    public List<ParentsCategoris> getParentsCategorises() {
        return parentsCategorises;
    }

    public void setParentsCategorises(List<ParentsCategoris> parentsCategorises) {
        this.parentsCategorises = parentsCategorises;
    }
}
