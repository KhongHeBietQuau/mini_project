package com.ms.tourist_app.application.dai;

import com.ms.tourist_app.domain.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {
    Department findByNameDepartment(String name);
}
