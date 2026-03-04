package com.uniovi.sdi.grademanager.controllers;

import com.uniovi.sdi.grademanager.entities.Department;
import com.uniovi.sdi.grademanager.services.DepartmentsService;
import com.uniovi.sdi.grademanager.validators.DepartmentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
public class DepartmentsController {

    @Autowired
    private DepartmentsService departmentsService;
    private final DepartmentValidator departmentValidator;

    public DepartmentsController(DepartmentValidator departmentValidator) {
        this.departmentValidator = departmentValidator;
    }

    @GetMapping("/departments")
    public String getDepartments(Model model, Pageable pageable,
                                 @RequestParam(value = "searchText", required = false) String searchText) {
        Page<Department> departments = departmentsService.getDepartments(pageable, searchText);
        model.addAttribute("departmentList", departments.getContent());
        model.addAttribute("page", departments);
        return "department/list";
    }
    @RequestMapping("/departments/update")
    public String updateList(Model model, Pageable pageable, @RequestParam(value = "searchText", required = false) String searchText) {
        Page<Department> departments = departmentsService.getDepartments(pageable, searchText);

        model.addAttribute("departmentList", departments.getContent());
        model.addAttribute("page", departments);

        // "nombre_archivo_html :: nombre_fragmento"
        return "department/list :: tableDepartments";
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
    public String setDepartment(@Validated @ModelAttribute Department department, BindingResult result) {
        departmentValidator.validate(department, result);
        if (result.hasErrors()) {
            return "department/add";
        }
        departmentsService.addDepartment(department);
        return "redirect:/departments";
    }

    @GetMapping("/departments/edit/{id}")
    public String getEdit(Model model, @PathVariable Long id) {
        model.addAttribute("department", departmentsService.getDepartment(id));
        return "department/edit";
    }

    @PostMapping("/departments/edit/{id}")
    public String setEdit(@PathVariable Long id, @ModelAttribute Department department) {
        department.setId(id);
        departmentsService.addDepartment(department);
        return "redirect:/departments";
    }
}