package com.layer.demo.controller;

import com.layer.demo.constants.Limits;
import com.layer.demo.domain.Access;
import com.layer.demo.domain.File;
import com.layer.demo.domain.Sheet;
import com.layer.demo.domain.User;
import com.layer.demo.model.*;
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
    public ResponseEntity<List<SharingResponse>> listSharing() {
        List<SharingResponse> sharingResponses = new ArrayList<>();
        List<Access> accessList = accessRepository.findAll();
        Map<String, SharingResponse> fileSharingMap = new HashMap<>();

        if(null != accessList) {
            for (Access access: accessList) {
                String fileName = access.getSheet().getFile().getFileName();
                String sheetName = access.getSheet().getSheetName();

                SharingResponse sharingResponse = fileSharingMap.get(fileName);
                if(null == sharingResponse) {
                    sharingResponse = new SharingResponse();
                    sharingResponse.setFileName(fileName);
                    sharingResponse.setSheetAccess(new HashMap<>());
                    fileSharingMap.put(fileName, sharingResponse);

                    sharingResponses.add(sharingResponse);
                }

                Map<String, List<CellRange>> userAccessMap = sharingResponse.getSheetAccess().computeIfAbsent(sheetName, k -> new HashMap<>());

                userAccessMap.put(access.getUser().getEmail(), access.getAccessMatrix().getCells());
            }
        }

        return new ResponseEntity<>(sharingResponses, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addSharing(@Valid @RequestBody SharingRequest sharingRequest) {
        List<User> users = new ArrayList<>();
        for (String email: sharingRequest.getEmails()) {
            User user = userRepository.findByEmail(email);

            if(null != user)
                users.add(user);
        }

        if(users.size() == 0) {
            new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        File file = fileRepository.findByFileName(sharingRequest.getFileName());
        if(null == file) {
            new ResponseEntity<>(sharingRequest, HttpStatus.BAD_REQUEST);
        }

        List<Sheet> SheetList = new ArrayList<>();
        Map<String, Selection> selectionMap = new HashMap<>();
        for(Selection selection: sharingRequest.getSelections()) {
            Sheet sheet = sheetRepository.findByFileIdAndSheetName(file.getFileId(), selection.getSheetName());
            if(null != sheet) {
                SheetList.add(sheet);
                selectionMap.put(selection.getSheetName(), selection);
            }
        }
        if(0 == SheetList.size()) {
            new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        for(User user: users) {
            for(Sheet sheet: SheetList) {
                Selection selection = selectionMap.get(sheet.getSheetName());
                Access access = accessRepository.findBySheetIdAndUserId(sheet.getSheetId(), user.getUserId());
                if(null != access) {
                    // TODO ADD merge logic
                    access.getAccessMatrix().getCells().add(selection.getCellRange());
                    accessRepository.save(access);
                } else {
                    access = new Access();
                    access.setUser(user);
                    access.setSheet(sheet);
                    access.setAccessMatrix(new AccessMatrix(new ArrayList<>()));

                    CellRange cellRange = selection.getCellRange();
                    if(null == cellRange) {
                        cellRange = new CellRange(Limits.FIRST_CELL, Limits.LAST_CELL);
                    }
                    access.getAccessMatrix().getCells().add(cellRange);
                    accessRepository.save(access);
                }
            }
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
