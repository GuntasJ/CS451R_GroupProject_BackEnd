package com.tags.cs451r_groupproject_backend.position.respository;

import com.tags.cs451r_groupproject_backend.position.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PositionRepository extends JpaRepository<Position, Long> {
    @Query("SELECT p FROM Position p WHERE p.positionClass = ?1")
    List<Position> findAllByPositionClass(String positionClass);
}
