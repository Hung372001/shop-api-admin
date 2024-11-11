package com.backend.shopapi.dto;

import com.backend.shopapi.model.ParentsCategoris;

import java.util.List;

public class ParentCategoriesResponse {
    private List<ParentsCategoris> data;
    private long totalCount;

    public ParentCategoriesResponse() {
    }

    public ParentCategoriesResponse(List<ParentsCategoris> data, long totalCount) {
        this.data = data;
        this.totalCount = totalCount;
    }

    public List<ParentsCategoris> getData() {
        return data;
    }

    public void setData(List<ParentsCategoris> data) {
        this.data = data;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }
}
