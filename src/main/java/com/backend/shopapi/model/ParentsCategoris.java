package com.backend.shopapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "parents_categoris")
public class ParentsCategoris {

    @Id()
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotBlank(message = "name required")
    private String name;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", nullable = false) // Ensure this field is required
    @JsonBackReference
    private GroupCategories groupCategories;

    public ParentsCategoris() {
    }

    public ParentsCategoris(String name, GroupCategories groupCategories) {
        this.name = name;
        this.groupCategories = groupCategories;
    }

    public @NotBlank(message = "name required") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "name required") String name) {
        this.name = name;
    }

    public GroupCategories getGroupCategories() {
        return groupCategories;
    }

    public void setGroupCategories( GroupCategories groupCategories) {
        this.groupCategories = groupCategories;
    }
}