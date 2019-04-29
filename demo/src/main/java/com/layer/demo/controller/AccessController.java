package com.layer.demo.controller;

import com.layer.demo.domain.Sheet;
import com.layer.demo.repository.AccessRepository;
import com.layer.demo.repository.FileRepository;
import com.layer.demo.repository.SheetRepository;
import com.layer.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/access")
public class AccessController {
    @Autowired
    AccessRepository accessRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    FileRepository fileRepository;
    @Autowired
    SheetRepository sheetRepository;

    @GetMapping("/list")
    public List<Sheet> getAllSheets() {
        return sheetRepository.findAll();
    }

    /*
    @PostMapping("/add")
    public Note createNote(@Valid @RequestBody Note note) {
        return noteRepository.save(note);
    }
    */
}
