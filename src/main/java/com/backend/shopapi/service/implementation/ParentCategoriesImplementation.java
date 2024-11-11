package com.backend.shopapi.service.implementation;

import com.backend.shopapi.dto.*;
import com.backend.shopapi.model.GroupCategories;
import com.backend.shopapi.model.ParentsCategoris;
import com.backend.shopapi.repository.GroupCategoriesRepository;
import com.backend.shopapi.repository.ParentCategoriesRepository;
import com.backend.shopapi.service.ParentCategoriesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParentCategoriesImplementation implements ParentCategoriesServices {
    @Autowired
    private ParentCategoriesRepository parentCategoriesRepository;

    @Autowired
    private GroupCategoriesRepository groupCategoriesRepository;

    @Override
    public ParentsCategoris createdParentCategory(int groupID,ParentsCategoris parentCategory) {
        parentCategory.setName(parentCategory.getName());
        GroupCategories groupCategories = groupCategoriesRepository.findById(groupID).orElseThrow(() -> new RuntimeException("Group not found"));
        parentCategory.setGroupCategories(groupCategories);
        ParentsCategoris newparentsCategoris = parentCategoriesRepository.save(parentCategory);
        return newparentsCategoris;
    }

    @Override
    public List<ParentsCategoris> findByGroupCategories(GroupCategories groupCategories) {
        List<ParentsCategoris> parentsCategorisList = parentCategoriesRepository.findByGroupCategories(groupCategories);
        return parentsCategorisList;
    }

    @Override
    public ParentCategoriesResponse getAllParentsCategories(String search, int page, int size) {
        Pageable pageable = PageRequest.of(page-1, size);
        Page<ParentsCategoris> parentsCategorises;

        if (search != null && !search.isEmpty()) {
            parentsCategorises = parentCategoriesRepository.findByNameContaining(search, pageable);
        } else {
            parentsCategorises = parentCategoriesRepository.findAll(pageable);
        }
        long totalCount = parentCategoriesRepository.count();

        List<ParentsCategoris> parentCategoriesList = parentsCategorises.getContent();
        return new ParentCategoriesResponse(parentCategoriesList, totalCount);
    }

    @Override
    public ParentCategoriesDto getParentCategoryById(int id) {
        ParentsCategoris parentsCategorisExist = parentCategoriesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parent category not found"));


        GroupCategories groupCategory = parentsCategorisExist.getGroupCategories();
        GroupCategoriesSimpleDto groupCategoriesDto = null;

        if (groupCategory != null) {
            groupCategoriesDto = new GroupCategoriesSimpleDto(groupCategory.getId(), groupCategory.getName());
        }
        return new ParentCategoriesDto(
                parentsCategorisExist.getId(),
                parentsCategorisExist.getName(),
                groupCategoriesDto
        );
    }

    @Override
    public ParentsCategoris updateParentCategory(int id, UpdateParentCategoryDto updateParentCategoryDto) {
        ParentsCategoris parentCategory = parentCategoriesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parent category not found"));

        // Update the name
        if (updateParentCategoryDto.getName() != null) {
            parentCategory.setName(updateParentCategoryDto.getName());
        }

        // Update the group category
        if (updateParentCategoryDto.getGroupId() > 0) {
            GroupCategories groupCategories = groupCategoriesRepository.findById(updateParentCategoryDto.getGroupId())
                    .orElseThrow(() -> new RuntimeException("Group category not found"));
            parentCategory.setGroupCategories(groupCategories);
        }

        return parentCategoriesRepository.save(parentCategory);
    }

    @Override
    public void deleteGroupCategories(int id) {
        parentCategoriesRepository.deleteById(id);
    }


    private ParentCategoriesDto convertToGroupCategoriesDto(ParentsCategoris parentsCategoris) {
        GroupCategories groupCategories = parentsCategoris.getGroupCategories();
        GroupCategoriesSimpleDto groupCategoriesDto = null;

        if (groupCategories != null) {
            groupCategoriesDto = new GroupCategoriesSimpleDto(groupCategories.getId(), groupCategories.getName());
        }

        return new ParentCategoriesDto(
                parentsCategoris.getId(),
                parentsCategoris.getName(),
                groupCategoriesDto
        );
    }
}
