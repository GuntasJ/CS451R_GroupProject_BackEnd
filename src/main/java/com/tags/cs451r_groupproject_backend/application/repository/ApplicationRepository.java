package com.tags.cs451r_groupproject_backend.application.repository;

import com.tags.cs451r_groupproject_backend.application.model.Application;
import com.tags.cs451r_groupproject_backend.filetransfer.model.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {


}
