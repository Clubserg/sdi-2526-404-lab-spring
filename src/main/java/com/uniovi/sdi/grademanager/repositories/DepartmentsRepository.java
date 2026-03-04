package com.uniovi.sdi.grademanager.repositories;

import com.uniovi.sdi.grademanager.entities.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentsRepository extends CrudRepository<Department, Long> {

    @Query("SELECT d FROM Department d WHERE (LOWER(d.name) LIKE LOWER(CONCAT('%', :searchText, '%')))")
    Page<Department> searchByName(Pageable pageable, String searchText);

    Page<Department> findAll(Pageable pageable);
}
