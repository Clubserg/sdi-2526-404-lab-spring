package com.uniovi.sdi.grademanager.validators;

import com.uniovi.sdi.grademanager.entities.Department;
import com.uniovi.sdi.grademanager.entities.Mark;
import com.uniovi.sdi.grademanager.entities.User;
import com.uniovi.sdi.grademanager.services.DepartmentsService;
import com.uniovi.sdi.grademanager.services.MarksService;
import com.uniovi.sdi.grademanager.services.UsersService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class DepartmentValidator implements Validator {

    private final DepartmentsService departmentsService;

    public DepartmentValidator(DepartmentsService departmentsService) {
        this.departmentsService = departmentsService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Department.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Department department = (Department) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "Error.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "faculty", "Error.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "Error.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "code", "Error.empty");

        checkTrim(department.getName(), "name", errors);
        checkTrim(department.getFaculty(), "faculty", errors);
        checkTrim(department.getPhone(), "phone", errors);

        if (department.getCode() != null) {
            if (department.getCode().length() == 9 || department.getCode().matches(".*[^a-zA-Z]$")) {
                errors.rejectValue("code", "Error.department.code.format");
            }

            if (departmentsService.getDepartmentByCode(department.getCode()) != null) {
                errors.rejectValue("code", "Error.department.code.duplicate");
            }
        }
    }

    private void checkTrim(String value, String field, Errors errors) {
        if (value != null && !errors.hasFieldErrors(field)) {
            if (!value.equals(value.trim())) {
                errors.rejectValue(field, "Error.whitespace");
            }
        }
    }
}