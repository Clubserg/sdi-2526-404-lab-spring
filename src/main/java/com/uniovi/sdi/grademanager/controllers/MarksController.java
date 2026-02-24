package com.uniovi.sdi.grademanager.controllers;

import com.uniovi.sdi.grademanager.entities.Mark;
import com.uniovi.sdi.grademanager.services.MarksService;
import com.uniovi.sdi.grademanager.services.UsersService;
import com.uniovi.sdi.grademanager.validators.GradeValidator;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@Controller
public class MarksController {

    private final MarksService marksService;
    private final UsersService usersService;
    private final GradeValidator gradeValidator;

    public MarksController(MarksService marksService, UsersService usersService, GradeValidator gradeValidator, HttpSession httpSession) {
        this.marksService = marksService;
        this.usersService = usersService;
        this.gradeValidator = gradeValidator;
    }


    @PostMapping("/mark/add")
    public String setMark(@Validated @ModelAttribute Mark mark, BindingResult result, Model model) {
        gradeValidator.validate(mark, result);
        if (result.hasErrors()) {
            model.addAttribute("usersList", usersService.getUsers());
            return "mark/add";
        }
        marksService.addMark(mark);
        return "redirect:/mark/list"; // Cambiado a redirect para evitar re-envíos del formulario
    }

    @RequestMapping("/mark/delete/{id}")
    public String deleteMark(@PathVariable Long id) {
        marksService.deleteMark(id);
        return "redirect:/mark/list";
    }

    @GetMapping("/mark/list")
    public String getList(Model model) {
        model.addAttribute("marksList", marksService.getMarks());
        return "mark/list";}

    @GetMapping("/mark/add")
    public String getMark(Model model) {
        model.addAttribute("mark", new Mark()); // Enviamos un objeto vacío para el binding
        model.addAttribute("usersList", usersService.getUsers());
        return "mark/add";
    }

    @GetMapping("/mark/details/{id}")
    public String getDetails(Model model, @PathVariable Long id) {
        model.addAttribute("mark", marksService.getMark(id));
        return "mark/details";
    }

    @GetMapping(value = "/mark/edit/{id}")
    public String getEdit(Model model, @PathVariable Long id) {
        model.addAttribute("mark", marksService.getMark(id));
        model.addAttribute("usersList", usersService.getUsers());
        return "mark/edit";
    }


    @PostMapping(value = "/mark/edit/{id}")
    public String setEdit(@ModelAttribute Mark mark, @PathVariable Long id) {
        Mark originalMark = marksService.getMark(id);
        originalMark.setScore(mark.getScore());
        originalMark.setDescription(mark.getDescription());
        marksService.addMark(originalMark);
        return "redirect:/mark/details/" + id;
    }

    @GetMapping("/mark/list/update")
    public String updateList(Model model){
        model.addAttribute("marksList", marksService.getMarks());
        return "mark/list :: marksTable";
    }

    @GetMapping("/mark/{id}/resend")
    public String setResendTrue(@PathVariable Long id) {
        marksService.setMarkResend(true, id);
        return "redirect:/mark/list";
    }
    @GetMapping("/mark/{id}/noresend")
    public String setResendFalse(@PathVariable Long id) {
        marksService.setMarkResend(false, id);
        return "redirect:/mark/list";
    }
}