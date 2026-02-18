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
        // Corregido: datos de ejemplo con códigos únicos para no disparar el validador nada más empezar
        departmentList.add(new Department(1L, "DEP01", "Informática", "Ingeniería", "985101010", 10));
        departmentList.add(new Department(2L, "DEP02", "Medicina", "Medicina", "985101011", 15));
        departmentList.add(new Department(3L, "DEP03", "Derecho", "Derecho", "985101012", 5));
    }

    public List<Department> getDepartmentList() {
        return departmentList;
    }

    public Department getDepartment(Long id) {
        return departmentList.stream()
                .filter(d -> d.getId().equals(id))
                .findFirst()
                .orElse(null);
    }


    public Department getDepartmentByCode(String code) {
        return departmentList.stream()
                .filter(d -> d.getCode().equals(code))
                .findFirst()
                .orElse(null);
    }

    public void addDepartment(Department department) {
        if (department.getId() == null) {
            // Seguridad: Si la lista está vacía empezamos en 1, si no, último + 1
            long nextId = departmentList.isEmpty() ? 1L : departmentList.getLast().getId() + 1;
            department.setId(nextId);
        }
        departmentList.add(department);
    }

    public void deleteDepartment(Long id) {
        departmentList.removeIf(d -> d.getId().equals(id));
    }
}