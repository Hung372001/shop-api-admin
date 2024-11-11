package com.backend.shopapi.service.implementation;

import com.backend.shopapi.dto.GroupCategoriesDto;
import com.backend.shopapi.dto.GroupCategoriesResponse;
import com.backend.shopapi.model.GroupCategories;
import com.backend.shopapi.model.ParentsCategoris;
import com.backend.shopapi.repository.GroupCategoriesRepository;
import com.backend.shopapi.repository.ParentCategoriesRepository;
import com.backend.shopapi.service.GroupsCategoriesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupsCategoriesImplementation implements GroupsCategoriesServices {
    @Autowired
    private GroupCategoriesRepository groupCategoriesRepository;

    @Autowired
    private ParentCategoriesRepository parentCategoriesRepository;

    @Override
    public GroupCategories createGroupCategories(GroupCategories groupCategories) {
        groupCategories.setName(groupCategories.getName());
        return groupCategoriesRepository.save(groupCategories) ;
    }

    @Override
    public GroupCategories findById(int id) {
        GroupCategories groupCategories =  groupCategoriesRepository.findById(id).orElse(null);
        return groupCategories;
    }

    @Override
    public GroupCategoriesResponse getAllGroupCategories(String search, int page, int size) {
        Pageable pageable = PageRequest.of(page-1, size);
        Page<GroupCategories> groupCategoriesPage;

        if (search != null && !search.isEmpty()) {
            groupCategoriesPage = groupCategoriesRepository.findByNameContaining(search, pageable);
        } else {
            groupCategoriesPage = groupCategoriesRepository.findAll(pageable);
        }
        long totalCount = groupCategoriesRepository.count();
        List<GroupCategoriesDto> groupCategoriesDtos = groupCategoriesPage.stream()
                .map(this::convertToGroupCategoriesDto)
                .collect(Collectors.toList());

        return new GroupCategoriesResponse(groupCategoriesDtos, totalCount);
    }

    @Override
    public GroupCategories updateGroupCategories(int id,GroupCategories newgroupCategories) {
        GroupCategories groupCategories = groupCategoriesRepository.findById(id).orElseThrow(()->new RuntimeException("Not Found"));
        groupCategories.setName(newgroupCategories.getName());
        groupCategoriesRepository.save(groupCategories);
        return groupCategories;
    }

    @Override
    public void deleteGroupCategories(int id) {
        GroupCategories groupCategories = groupCategoriesRepository.findById(id).orElseThrow(()->new RuntimeException("Not Found"));
        groupCategoriesRepository.deleteById(id);

    }


    private GroupCategoriesDto convertToGroupCategoriesDto(GroupCategories groupCategories) {
        List<ParentsCategoris> parentsCategorises = parentCategoriesRepository.findByGroupCategories(groupCategories); // Fetch associated parents
        return new GroupCategoriesDto(
                groupCategories.getId(),
                groupCategories.getName(),
                parentsCategorises
        );
    }


}
