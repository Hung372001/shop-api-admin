package com.backend.shopapi.controller;

import com.backend.shopapi.dto.*;
import com.backend.shopapi.model.GroupCategories;
import com.backend.shopapi.model.ParentsCategoris;
import com.backend.shopapi.service.ParentCategoriesServices;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("parent-categories")
public class ParentCategoriesController {

    @Autowired
    private ParentCategoriesServices parentCategoriesServices;

    @PostMapping("")
    public ResponseEntity<?> createdParentsCategories(@Valid @RequestBody CreateParentCategoryDto createParentCategoryDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(new ApiResponse("Validation failed", 400, errors));
        }

        ParentsCategoris parentsCategoris = new ParentsCategoris();
        parentsCategoris.setName(createParentCategoryDto.getName());
        ParentsCategoris parentsCategoris1 = parentCategoriesServices.createdParentCategory(createParentCategoryDto.getGroupId(), parentsCategoris);

        if (parentsCategoris1 == null) {
            return ResponseEntity.badRequest().body(new ApiResponse("Parents Categories creation failed", 400));
        }
        return ResponseEntity.status(201).body(new ApiResponse("Parents Categories success", 201));
    }

    @GetMapping("")
    public ResponseEntity<ParentCategoriesResponse> getAll(
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "1") @Min(1) int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        ParentCategoriesResponse response = parentCategoriesServices.getAllParentsCategories(search, page, size);
        return ResponseEntity.ok(response);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ParentCategoriesDto> getParentCategoryById(@PathVariable int id) {
        ParentCategoriesDto parentCategory = parentCategoriesServices.getParentCategoryById(id);
        return ResponseEntity.ok(parentCategory);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateParentCategory(@PathVariable("id") int id,
                                                  @Valid @RequestBody UpdateParentCategoryDto updateDto,
                                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(new ApiResponse("Validation failed", 400, errors));
        }

        ParentsCategoris updatedParentCategory = parentCategoriesServices.updateParentCategory(id, updateDto);
        return ResponseEntity.ok(new ApiResponse("Parent category updated successfully", 200));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?>Delete(@PathVariable("id") int id) {

            parentCategoriesServices.deleteGroupCategories(id);
            return ResponseEntity.status(200).body(new ApiResponse(" Delete Group Categories success", 200));

    }
}
