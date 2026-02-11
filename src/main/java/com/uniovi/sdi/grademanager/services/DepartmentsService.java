package com.uniovi.sdi.grademanager.services;

import com.uniovi.sdi.grademanager.entities.Department;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class DepartmentsService {

    private final List<Department> departmentList = new LinkedList<>();
    @PostConstruct
    public void init() {
        /*...*/
        departmentList.add(new Department( 1L,"1", "DEP-01", "Informatica", "+1", 1));
        departmentList.add(new Department(2L, "1", "DEP-02", "Medicina", "+2", 1));
        departmentList.add(new Department(3L, "1", "DEP-03", "Derecho", "+3", 1));
    }

    public List<Department> getDepartmentList() {
        return departmentList;
    }

    public Department getDepartment(Long id) {
        return departmentList.stream()
                .filter(mark -> mark.getId().equals(id)).findFirst().orElse(null);
    }

    public void addDepartment(Department department) {
        // Si en Id es null le asignamos el ultimo + 1 de la lista
        if (department.getId() == null) {
            department.setId(departmentList.getLast().getId() + 1);
        }
        departmentList.add(department);
    }
    public void deleteDepartment(Long id) {
        departmentList.removeIf(mark -> mark.getId().equals(id));
    }


}
