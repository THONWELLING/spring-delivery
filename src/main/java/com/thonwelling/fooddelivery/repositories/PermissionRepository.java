package com.thonwelling.fooddelivery.repositories;

import com.thonwelling.fooddelivery.models.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, UUID> {}