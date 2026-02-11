package com.uniovi.sdi.grademanager.controllers;

import com.uniovi.sdi.grademanager.entities.Department;
import com.uniovi.sdi.grademanager.services.DepartmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class DepartmentsController {

    @Autowired
    private DepartmentsService departmentsService;

    @GetMapping("/departments")
    public String getDepartments() {
        return departmentsService.getDepartmentList().toString();
    }

    @GetMapping("/departments/details/{id}")
    public String getDepartmentDetails(@PathVariable Long id) {
        return departmentsService.getDepartment(id).toString();
    }

    @GetMapping("/departments/delete/{id}")
    public String deleteDepartment(@PathVariable Long id) {
        departmentsService.deleteDepartment(id);
        return "eliminando departamento" + id;
    }

    @GetMapping("/departments/add")
    public String addDepartment() {

        return "añadir departamento";
    }

    @PostMapping(value = "/departments/add")
    public String setDepartment(@ModelAttribute Department department) {
        departmentsService.addDepartment(department);
        return "añadiendo departamento";
    }

    @RequestMapping(value = "/departments/edit/{id}")
    public String getEdit(@PathVariable Long id) {
        return "editando departamento " + id;
    }

    @PostMapping("/departments/edit")
    public String setEdit(@ModelAttribute Department department) {
        departmentsService.addDepartment(department);
        return "editando departamento";
    }


}
