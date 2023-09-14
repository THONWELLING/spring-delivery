package com.thonwelling.fooddelivery.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Group implements Serializable {
  @Serial
  private static final long serialVersionUID =1L;

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private Long id;

  @Column(nullable = false)
  private String name;

  @ManyToMany
  @JoinTable(name = "group_permission", joinColumns = @JoinColumn(name = "group_id"), inverseJoinColumns = @JoinColumn(name = "permission_id"))
  private List<Permission> permissions = new ArrayList<>();

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Group group)) return false;
    return Objects.equals(getId(), group.getId()) && Objects.equals(getName(), group.getName()) && Objects.equals(getPermissions(), group.getPermissions());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getName(), getPermissions());
  }
}
