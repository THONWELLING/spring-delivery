package com.thonwelling.fooddelivery.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "collection")
public class Collection implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @Column(nullable = false, length = 30)
  private String name;

  @ManyToMany
  @JoinTable(name = "collection_permission", joinColumns = @JoinColumn(name = "collection_id"), inverseJoinColumns = @JoinColumn(name = "permission_id"))
  private List<Permission> permissions = new ArrayList<>();

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Collection that = (Collection) o;
    return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(permissions, that.permissions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, permissions);
  }
}
