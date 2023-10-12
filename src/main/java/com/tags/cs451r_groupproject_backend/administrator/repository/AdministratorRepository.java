package com.tags.cs451r_groupproject_backend.administrator.repository;

import com.tags.cs451r_groupproject_backend.administrator.model.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministratorRepository extends JpaRepository<Administrator, Long> {
}
