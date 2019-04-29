package com.layer.demo.controller;

import com.layer.demo.domain.Access;
import com.layer.demo.domain.Sheet;
import com.layer.demo.model.SharingRequest;
import com.layer.demo.repository.AccessRepository;
import com.layer.demo.repository.FileRepository;
import com.layer.demo.repository.SheetRepository;
import com.layer.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public List<Access> listAccess() {
        return accessRepository.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<SharingRequest> addSharing(@Valid @RequestBody SharingRequest sharingRequest) {

        return new ResponseEntity<SharingRequest>(sharingRequest, HttpStatus.CREATED);
    }
}
