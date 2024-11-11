package com.backend.shopapi.dto;

import java.util.List;

public class GroupCategoriesResponse {
    private List<GroupCategoriesDto> data;
    private long totalCount;

    public GroupCategoriesResponse(List<GroupCategoriesDto> data, long totalCount) {
        this.data = data;
        this.totalCount = totalCount;
    }

    public List<GroupCategoriesDto> getGroupCategories() {
        return data;
    }

    public void setGroupCategories(List<GroupCategoriesDto> data) {
        this.data = data;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }
}