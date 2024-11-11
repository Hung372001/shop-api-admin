package com.backend.shopapi.controller;

import com.backend.shopapi.dto.ApiResponse;
import com.backend.shopapi.dto.GroupCategoriesDto;
import com.backend.shopapi.dto.GroupCategoriesResponse;
import com.backend.shopapi.model.GroupCategories;
import com.backend.shopapi.service.GroupsCategoriesServices;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/category-group")
public class GroupCategoriesController {
    @Autowired
    private  GroupsCategoriesServices groupsCategoriesServices;
    @PostMapping("")
    public ResponseEntity<?>createdGroupCategories(@Valid @RequestBody GroupCategories groupCategories, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String,String> errors = new HashMap<>();
            for ( FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return  ResponseEntity.badRequest().body(new ApiResponse("Validation failed", 400, errors));
        }
        GroupCategories newGroupCategories = groupsCategoriesServices.createGroupCategories(groupCategories);
        if (groupCategories == null) {
            return ResponseEntity.badRequest().body(new ApiResponse("Group Categories creation failed", 400));
        }
        return ResponseEntity.status(201).body(new ApiResponse("Group Categories success", 201));
    }

    @GetMapping("")
    public ResponseEntity<GroupCategoriesResponse> getAllGroupCategoriesWithParents(
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "1")  @Min(1) int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        System.out.printf("page"+page);
        GroupCategoriesResponse response = groupsCategoriesServices.getAllGroupCategories(search, page, size);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?>updatedGroupCategories(@PathVariable("id") int id, @Valid @RequestBody GroupCategories groupCategories, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String,String> errors = new HashMap<>();
            for ( FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return  ResponseEntity.badRequest().body(new ApiResponse("Validation failed", 400, errors));
        }
        GroupCategories newGroupCategories = groupsCategoriesServices.updateGroupCategories(id,groupCategories);

        if (groupCategories == null) {
            return ResponseEntity.badRequest().body(new ApiResponse("Group Categories updated failed", 400));
        }
        return ResponseEntity.status(200).body(new ApiResponse("Group Categories success", 201));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?>updatedGroupCategories(@PathVariable("id") int id) {
        try{
            groupsCategoriesServices.deleteGroupCategories(id);
            return ResponseEntity.status(200).body(new ApiResponse(" Delete Group Categories success", 200));
        }catch (DataIntegrityViolationException ex){
            String errorMessage = getErrorMessage(ex.getMessage());
             return ResponseEntity.badRequest().body(new ApiResponse(errorMessage, 400));
        }
    }

    public String getErrorMessage(String originalMessage) {
        if (originalMessage.contains("Cannot delete or update a parent row: a foreign key constraint fails")) {
            return "Không thể xóa hoặc cập nhật danh mục cha này vì nó đang được sử dụng bởi các danh mục con.";
        } else {
            return originalMessage; // Trả về thông báo lỗi gốc nếu không khớp
        }
    }


}
