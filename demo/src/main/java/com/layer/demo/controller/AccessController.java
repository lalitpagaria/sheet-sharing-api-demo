package com.layer.demo.controller;

import com.layer.demo.domain.Access;
import com.layer.demo.domain.File;
import com.layer.demo.domain.Sheet;
import com.layer.demo.domain.User;
import com.layer.demo.model.AccessMatrix;
import com.layer.demo.model.Selection;
import com.layer.demo.model.SharingRequest;
import com.layer.demo.repository.AccessRepository;
import com.layer.demo.repository.FileRepository;
import com.layer.demo.repository.SheetRepository;
import com.layer.demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
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
        List<User> users = new ArrayList<>();
        for (String email: sharingRequest.getEmails()) {
            User user = userRepository.findByEmail(email);

            if(user != null)
                users.add(user);
        }

        if(users.size() == 0) {
            new ResponseEntity<SharingRequest>(sharingRequest, HttpStatus.BAD_REQUEST);
        }

        File file = fileRepository.findByFileName(sharingRequest.getFileName());
        if(file == null) {
            new ResponseEntity<SharingRequest>(sharingRequest, HttpStatus.BAD_REQUEST);
        }

        List<Sheet> SheetList = new ArrayList<>();
        Map<String, Selection> selectionMap = new HashMap<>();
        for(Selection selection: sharingRequest.getSelections()) {
            Sheet sheet = sheetRepository.findByFileIdAndSheetName(file.getFileId(), selection.getSheetName());
            if(sheet != null) {
                SheetList.add(sheet);
                selectionMap.put(selection.getSheetName(), selection);
            }
        }
        if(SheetList.size() == 0) {
            new ResponseEntity<SharingRequest>(sharingRequest, HttpStatus.BAD_REQUEST);
        }

        for(User user: users) {
            for(Sheet sheet: SheetList) {
                Selection selection = selectionMap.get(sheet.getSheetName());
                Access access = accessRepository.findBySheetIdAndUserId(sheet.getSheetId(), user.getUserId());
                if(access != null) {
                    access.getAccessMatrix().getCells().add(selection.getCellRange());
                    accessRepository.save(access);
                } else {
                    access = new Access();
                    access.setAccessMatrix(new AccessMatrix());
                    access.setUser(user);
                    access.setSheet(sheet);

                    if(selection.getCellRange() == null) {
                        access.getAccessMatrix().setIsWholeSheet(true);
                    } else {
                        access.getAccessMatrix().setIsWholeSheet(false);
                        access.getAccessMatrix().setCells(new ArrayList<>());
                        access.getAccessMatrix().getCells().add(selection.getCellRange());
                    }
                    accessRepository.save(access);
                }
            }
        }

        return new ResponseEntity<SharingRequest>(sharingRequest, HttpStatus.CREATED);
    }
}
