package com.backend.shopapi.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "group_categories")
public class GroupCategories {
  @Id()
  @GeneratedValue(strategy =  GenerationType.IDENTITY)
  private int id;

  @Column(name = "name")
  @NotBlank(message = "name required")
  private String name;

  @OneToMany(mappedBy = "groupCategories")
  @JsonManagedReference
  private List<ParentsCategoris>  parentsCategorisList = new ArrayList<>();

  public GroupCategories() {
  }

  public GroupCategories(String name) {
    this.name = name;
  }

  public @NotBlank(message = "name required") String getName() {
    return name;
  }

  public void setName(@NotBlank(message = "name required") String name) {
    this.name = name;
  }

  public List<ParentsCategoris> getParentsCategorisList() {
    return parentsCategorisList;
  }

  public void setParentsCategorisList(List<ParentsCategoris> parentsCategorisList) {
    this.parentsCategorisList = parentsCategorisList;
  }
}