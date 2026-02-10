package com.uniovi.sdi.grademanager.controllers;

import com.uniovi.sdi.grademanager.entities.Mark;
import com.uniovi.sdi.grademanager.services.MarksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MarksController {

    @Autowired  //Inyectar el servicio
    private MarksService marksService;

    @PostMapping("/mark/add")
    public String setMark(@ModelAttribute Mark mark) {
        marksService.addMark(mark);
        return "Mark Added";
    }
    @RequestMapping(value = "/mark/list", method = RequestMethod.GET)
    public String getList() {
        return marksService.getMarks().toString();
    }
    @GetMapping(value = "/mark/details/{id}")
    public String getDetails(@PathVariable Long id) {
        return marksService.getMark(id).toString();
    }
    @RequestMapping("/mark/delete/{id}")
    public String deleteMark(@PathVariable Long id) {
        marksService.deleteMark(id);
        return "Mark deleted";
    }


}