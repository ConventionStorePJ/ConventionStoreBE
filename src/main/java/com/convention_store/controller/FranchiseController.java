package com.convention_store.controller;

import com.convention_store.dto.FranchiseDto;
import com.convention_store.service.FranchiseService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/franchises")
@RequiredArgsConstructor
public class FranchiseController {
    
    private final FranchiseService franchiseService;
    
    @GetMapping("")
    public ResponseEntity<List<FranchiseDto>> getAllFranchises() {
        return ResponseEntity.ok(franchiseService.getAllFranchises());
    }
}
