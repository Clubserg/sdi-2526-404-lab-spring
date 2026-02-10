package com.uniovi.sdi.grademanager.controllers;

import com.uniovi.sdi.grademanager.entities.Mark;
import org.springframework.web.bind.annotation.*;

@RestController
public class MarksController {

    @RequestMapping("/mark/list")
    public String getList() {
        return "Getting List";
    }

    @PostMapping("/mark/add")
    public String setMark(@ModelAttribute Mark mark) {
        return "Added: " + mark.getDescription()
                + " with score: " + mark.getScore()
                + " id: " + mark.getId();
    }

    @GetMapping(value= "/mark/details/{id}")
    public String getDetails(@PathVariable Long id ) {
        return "Getting Details con => @GetMapping" + id;
    }



}