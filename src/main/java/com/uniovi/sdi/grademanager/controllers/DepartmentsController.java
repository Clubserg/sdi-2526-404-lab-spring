package com.uniovi.sdi.grademanager.controllers;

import com.uniovi.sdi.grademanager.entities.Department;
import com.uniovi.sdi.grademanager.services.DepartmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class DepartmentsController {

    @Autowired
    private DepartmentsService departmentsService;

    @GetMapping("/departments")
    public String getDepartments(Model model) {
        model.addAttribute("departmentList", departmentsService.getDepartmentList());
        return "department/list";
    }

    @GetMapping("/departments/details/{id}")
    public String getDepartmentDetails(Model model, @PathVariable Long id) {
        model.addAttribute("department", departmentsService.getDepartment(id));
        return "department/details";
    }

    @GetMapping("/departments/delete/{id}")
    public String deleteDepartment(@PathVariable Long id) {
        departmentsService.deleteDepartment(id);
        return "redirect:/departments";
    }

    @GetMapping("/departments/add")
    public String addDepartment(Model model) {
        model.addAttribute("department", new Department());
        return "department/add";
    }

    @PostMapping(value = "/departments/add")
    public String setDepartment(@ModelAttribute Department department) {
        departmentsService.addDepartment(department);
        return "redirect:/departments";
    }

    @GetMapping("/departments/edit/{id}")
    public String getEdit(Model model, @PathVariable Long id) {
        model.addAttribute("department", departmentsService.getDepartment(id));
        return "department/edit";
    }

    // Procesar la edición
    @PostMapping("/departments/edit/{id}")
    public String setEdit(@PathVariable Long id, @ModelAttribute Department department) {
        department.setId(id);
        departmentsService.addDepartment(department);
        return "redirect:/departments";
    }
}