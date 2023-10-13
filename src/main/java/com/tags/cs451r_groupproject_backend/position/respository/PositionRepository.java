package com.tags.cs451r_groupproject_backend.position.respository;

import com.tags.cs451r_groupproject_backend.position.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Long> {
}
